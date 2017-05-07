package be.vdab.repositories;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import be.vdab.entities.Filiaal;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.PostcodeReeks;

@Repository
public class InMemoryFiliaalRepository implements FiliaalRepository {

    private static final String BEGIN_SQL = 
	    "select id, naam, hoofdFiliaal, straat, huisNr, postcode, gemeente," + 
	    "inGebruikName, waardeGebouw from filialen "; 
    private static final String SQL_FIND_ALL = BEGIN_SQL + "order by naam"; 
    private static final String SQL_FIND_BY_POSTCODE = BEGIN_SQL + 
	    "where postcode between :van and :tot order by naam"; 
    private static final String SQL_READ = BEGIN_SQL + "where id = :id";
    private static final String SQL_FIND_AANTAL_FILIALEN =  
	    "select count(*) from filialen"; 
    private static final String SQL_FIND_AANTAL_WERKNEMERS =  
	    "select count(*) from werknemers where filiaalId = ?"; 
    
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    private final RowMapper<Filiaal> rowMapper = (resultSet, rowNum) -> 
    	new Filiaal(
    		resultSet.getLong("id"),
    		resultSet.getString("naam"), 
    		resultSet.getBoolean("hoofdFiliaal"), 
    		resultSet.getBigDecimal("waardeGebouw"),
    	    	resultSet.getDate("inGebruikName").toLocalDate(), 
    	    	new Adres(
    	    		resultSet.getString("straat"),
    	    		resultSet.getString("huisNr"), 
    	    		resultSet.getInt("postcode"), 
    	    		resultSet.getString("gemeente")));

    InMemoryFiliaalRepository(JdbcTemplate jdbcTemplate, 
	    NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
	this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    
    @Override
    public List<Filiaal> findAll() {
	return jdbcTemplate.query(SQL_FIND_ALL, rowMapper);
    }

    @Override
    public long findAantalWerknemers(long id) {
	return jdbcTemplate.queryForObject(SQL_FIND_AANTAL_WERKNEMERS, Long.class, id);
    }

    @Override
    public List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks) {
	Map<String, Object> parameters = new HashMap<>();
	parameters.put("van", reeks.getVanPostcode());
	parameters.put("tot", reeks.getTotPostcode());
	return namedParameterJdbcTemplate.query(SQL_FIND_BY_POSTCODE, parameters, rowMapper);
    }

    @Override
    public void create(Filiaal filiaal) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public Optional<Filiaal> read(long id) {
	Map<String, Long> parameters = Collections.singletonMap("id", id);
	try {
	    return Optional.of(namedParameterJdbcTemplate.queryForObject(SQL_READ, parameters, rowMapper));
	} catch (IncorrectResultSizeDataAccessException ex) {
	    return Optional.empty(); // record niet gevonden
	}
    }

    @Override
    public void update(Filiaal filiaal) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void delete(long id) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public long findAantalFilialen() {
	return jdbcTemplate.queryForObject(SQL_FIND_AANTAL_FILIALEN, Long.class);
    }

}
