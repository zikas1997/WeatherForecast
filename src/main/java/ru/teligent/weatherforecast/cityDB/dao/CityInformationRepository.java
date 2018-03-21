package ru.teligent.weatherforecast.cityDB.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.teligent.weatherforecast.cityDB.model.CityInformation;

@Repository
public interface CityInformationRepository extends JpaRepository<CityInformation,Long>{
}
