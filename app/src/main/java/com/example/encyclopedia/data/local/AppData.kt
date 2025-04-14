package com.example.encyclopedia.data.local

import com.example.encyclopedia.presentation.viewmodel.AnimalCat

class AppData() {
    fun loadAnimals():List<AnimalCat>{
        return listOf<AnimalCat>(
            AnimalCat("Mammals","Category:Mammal_common_names"),
            AnimalCat("Birds","Category:Bird_common_names"),
            AnimalCat("Reptiles","Category:Reptile_common_names"),
            AnimalCat("Aquatic Animals", "Category:Fish_common_names")
        )
    }
}