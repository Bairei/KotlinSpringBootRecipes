package com.bairei.springrecipes.services

import java.util.stream.Collectors
import java.util.stream.StreamSupport
import com.bairei.springrecipes.commands.UnitOfMeasureCommand
import com.bairei.springrecipes.converters.UnitOfMeasureToUnitOfMeasureCommand
import com.bairei.springrecipes.repositories.UnitOfMeasureRepository
import org.springframework.stereotype.Service
import java.util.stream.Collector



@Service
class UnitOfMeasureServiceImpl(private val unitOfMeasureRepository: UnitOfMeasureRepository, private val unitOfMeasureToUnitOfMeasureCommand: UnitOfMeasureToUnitOfMeasureCommand) : UnitOfMeasureService {

    override fun listAllUoms(): Set<UnitOfMeasureCommand> {

        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(), false)
                .map<UnitOfMeasureCommand>( { unitOfMeasureToUnitOfMeasureCommand.convert(it) })
                .collect<Set<UnitOfMeasureCommand>, Any>(Collectors.toSet<UnitOfMeasureCommand>() as Collector<in UnitOfMeasureCommand, Any, Set<UnitOfMeasureCommand>>)

        // this probably WILL NOT work in the future Kotlin releases!!!
    }
}