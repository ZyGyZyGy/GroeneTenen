package be.vdab.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import be.vdab.entities.Filiaal;
import be.vdab.valueobjects.PostcodeReeks;

@Repository
class JpaFiliaalRepository implements FiliaalRepository {

    private EntityManager entityManager;

    @PersistenceContext
    void setEntityManager(EntityManager entityManager) {
	this.entityManager = entityManager;
    }

    @Override
    public void create(Filiaal filiaal) {
	entityManager.persist(filiaal);
    }

    @Override
    public Optional<Filiaal> read(long id) {
	return Optional.ofNullable(entityManager.find(Filiaal.class, id));
    }

    @Override
    public void update(Filiaal filiaal) {
	entityManager.merge(filiaal);
    }

    @Override
    public void delete(long id) {
	entityManager.remove(read(id).get());
    }

    @Override
    public List<Filiaal> findAll() {
	return entityManager.createNamedQuery("Filiaal.findAll", Filiaal.class)
		.getResultList();
    }

    @Override
    public List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks) {
	return entityManager.createNamedQuery("Filiaal.findByPostcodeReeks", Filiaal.class)
		.setParameter("van", reeks.getVanPostcode())
		.setParameter("tot", reeks.getTotPostcode())
		.getResultList();
    }

    @Override
    public long findAantalFilialen() {
	return entityManager.createNamedQuery("Filiaal.findAantal", Number.class)
		.getSingleResult().longValue();
    }

    @Override
    public long findAantalWerknemers(long id) {
	// TODO Auto-generated method stub
	return 0;
    }
}
