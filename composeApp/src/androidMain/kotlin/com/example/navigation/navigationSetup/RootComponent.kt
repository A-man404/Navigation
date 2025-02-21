package com.example.navigation.navigationSetup

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.serialization.Serializable

class RootComponent(componentContext: ComponentContext) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Configurations>()
//
//    val childStack = childStack(
//        source = navigation,
//        serializer = Configurations.serializer(),
//        initialConfiguration = Child.ScreenA,
//        handleBackButton = true,
//        childFactory = ::createChild
//    )


    private fun createChild(config: Configurations, context: ComponentContext): Child {
        return when (config) {
            Configurations.ScreenA -> Child.ScreenA(
                ScreenAComponent(context)
            )

            is Configurations.ScreenB -> Child.ScreenB(
                ScreenBComponent(config.name, context)
            )
        }
    }

    sealed class Child {
        data class ScreenA(val screenAComponent: ScreenAComponent) : Child()
        data class ScreenB(val screenAComponent: ScreenBComponent) : Child()
    }


    @Serializable
    sealed class Configurations {
        @Serializable
        data object ScreenA : Configurations()

        @Serializable
        data class ScreenB(val name: String) : Configurations()
    }
}