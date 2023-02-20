package com.dfm.Estates;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dfm.Estates.Entities.EstateModel;

public interface IEstatesRepository extends JpaRepository<EstateModel, Integer>
{
    public Optional<EstateModel> findByDescription(String description);
}
