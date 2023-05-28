package com.h_fahmy.profile_presentation

import com.h_fahmy.profile_domain.model.UserProfile

data class ProfileState(
    val userProfile: UserProfile? = null,
    val invalidCarbRatio: Boolean = false,
    val invalidProteinRatio: Boolean = false,
    val invalidFatRatio: Boolean = false,
)
