package org.example.pokedex.domain.model

import org.example.pokedex.data.dto.ChainDto

data class Evolution(
    val id: Long,
    val page: Long,
    val chain: ChainDto,
) {
    val imageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}
