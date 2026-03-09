package com.jrkg.jrkgbites.services

import com.jrkg.jrkgbites.model.Restaurant

class FilterService {
    companion object {
        fun generateCategoryFilter(restaurantList: List<Restaurant>): List<String> {
            return generateFilter(restaurantList, FilterGroup.Category)
        }

        fun generateCuisineFilter(restaurantList: List<Restaurant>): List<String> {
            return generateFilter(restaurantList, FilterGroup.Cuisine)
        }

        fun generateLevelFilter(restaurantList: List<Restaurant>): List<String> {
            return generateFilter(restaurantList, FilterGroup.Level)
        }

        fun generateLocationFilter(restaurantList: List<Restaurant>): List<String> {
            return generateFilter(restaurantList, FilterGroup.Location)
        }

        fun generateTagsFilter(restaurantList: List<Restaurant>): List<String> {
            return generateFilter(restaurantList, FilterGroup.Tags)
        }

//        fun generateAddedByFilter(restaurantList: List<Restaurant>): List<String> {
//            return generateFilter(restaurantList, FilterGroup.Added_by)
//        }

        private fun generateFilter(restaurantList: List<Restaurant>, filterName: FilterGroup): List<String> {
            val categorySet = mutableSetOf<String>()

            restaurantList.forEach { restaurant ->
                when (filterName) {
                    FilterGroup.Category -> restaurant.category?.takeIf { it.isNotBlank() }?.let { categorySet.add(it) }
                    FilterGroup.Cuisine -> restaurant.cuisine?.takeIf { it.isNotBlank() }?.let { categorySet.add(it) }
                    FilterGroup.Level -> restaurant.level?.takeIf { it.isNotBlank() }?.let { categorySet.add(it) }
                    FilterGroup.Location -> restaurant.location?.takeIf { it.isNotBlank() }?.let { categorySet.add(it) }
                    FilterGroup.Tags -> restaurant.tags?.forEach { tag ->
                        if (tag.isNotBlank()) categorySet.add(tag)
                    }
                    else -> {}
//                    FilterGroup.Added_by -> restaurant.addedBy?.takeIf { it == getCurrentUserId() }?.let { categorySet.add(it) }
                }
            }

            return listOf("All") + categorySet.sorted()
        }
    }

    enum class FilterGroup {
        Category,
        Cuisine,
        Level,
        Location,
        Tags,
        Added_by
    }
}

private fun FilterService.Companion.getCurrentUserId() {
    TODO("Not yet implemented")
}
