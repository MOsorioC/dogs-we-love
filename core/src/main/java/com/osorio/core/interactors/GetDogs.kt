package com.osorio.core.interactors

import com.osorio.core.domain.DogService

class GetDogs(private  val dogService: DogService) {
    suspend operator fun invoke() = dogService.getDogs()
}