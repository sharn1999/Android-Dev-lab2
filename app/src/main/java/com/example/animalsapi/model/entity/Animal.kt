package com.example.animalsapi.model.entity

data class Animal(
    val name: String,
    val taxonomy: Taxonomy,
    val locations: List<String>,
    val characteristics: Characteristics
)