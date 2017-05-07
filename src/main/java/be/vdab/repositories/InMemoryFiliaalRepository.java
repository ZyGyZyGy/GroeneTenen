package be.vdab.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import be.vdab.entities.Filiaal;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.PostcodeReeks;

@Repository
public class InMemoryFiliaalRepository implements FiliaalRepository {

    private final Map<Long, Filiaal> filialen = new ConcurrentHashMap<>();
    
    private static final String BEGIN_SQL = 
	    "select id, naam, hoofdFiliaal, straat, huisNr, postcode, gemeente," + 
	    "inGebruikName, waardeGebouw from filialen "; 
    private static final String SQL_FIND_ALL = BEGIN_SQL + "order by naam"; 
    
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
    public void create(Filiaal filiaal) {
	filiaal.setId(Collections.max(filialen.keySet()) + 1);
	filialen.put(filiaal.getId(), filiaal);
    }

    @Override
    public Optional<Filiaal> read(long id) {
	return Optional.ofNullable(filialen.get(id));
    }

    @Override
    public void update(Filiaal filiaal) {
	filialen.put(filiaal.getId(), filiaal);
    }

    @Override
    public void delete(long id) {
	filialen.remove(id);
    }

    @Override
    public List<Filiaal> findAll() {
	return jdbcTemplate.query(SQL_FIND_ALL, rowMapper);
    }

    @Override
    public long findAantalFilialen() {
	return filialen.size();
    }

    @Override
    public long findAantalWerknemers(long id) {
	return id == 1L ? 7L : 0L;
    }

    @Override
    public List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks) {
	return filialen.values().stream().filter(filiaal -> reeks.bevat(filiaal.getAdres().getPostcode()))
		.collect(Collectors.toList());
    }

}
