package com.plcoding.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.plcoding.calorytracker.ui.theme.CaloryTrackerTheme
import com.plcoding.core.data.preferences.Preferences
import com.plcoding.calorytracker.navigation.Route
import com.plcoding.onboarding_presentation.activity.ActivityScreen
import com.plcoding.onboarding_presentation.age.AgeScreen
import com.plcoding.onboarding_presentation.gender.GenderScreen
import com.plcoding.onboarding_presentation.goal.GoalScreen
import com.plcoding.onboarding_presentation.height.HeightScreen
import com.plcoding.onboarding_presentation.nutrient_goal.NutrientGoalScreen
import com.plcoding.onboarding_presentation.weight.WeightScreen
import com.plcoding.onboarding_presentation.welcome.WelcomeScreen
import com.plcoding.tracker_presentation.search.SearchScreen
import com.plcoding.tracker_presentation.tracker_overview.TrackerOverviewScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint //need to tell hilt that it can see this
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerTheme {
                val navController = rememberNavController() // nav controller needed for nav in compose
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost( //nav host with start destination for the app
                        navController = navController,
                        startDestination = if (preferences.loadShouldShowOnboarding()) Route.WELCOME
                                            else Route.TRACKER_OVERVIEW
                    ){ //define the composable that will be part of the nav graph
                        composable(route = Route.WELCOME){
                            //important to use the right navigate
                            WelcomeScreen(onNextClick = {
                                navController.navigate(Route.GENDER)
                            })
                        }
                        composable(route = Route.AGE){
                            AgeScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.HEIGHT)
                                }
                            )
                        }
                        composable(route = Route.GENDER){
                            GenderScreen(onNextClick = {
                                navController.navigate(Route.AGE)
                            })
                        }
                        composable(route = Route.HEIGHT){
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.WEIGHT)
                                }
                            )
                        }
                        composable(route = Route.WEIGHT){
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.ACTIVITY)
                                }
                            )
                        }
                        composable(route = Route.NUTRIENT_GOAL){
                            NutrientGoalScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.TRACKER_OVERVIEW)
                                }
                            )
                        }
                        composable(route = Route.ACTIVITY){
                            ActivityScreen(onNextClick = {
                                navController.navigate(Route.GOAL)
                            })
                        }
                        composable(route = Route.GOAL){
                            GoalScreen(onNextClick = {
                                navController.navigate(Route.NUTRIENT_GOAL)
                            })
                        }
                        composable(route = Route.TRACKER_OVERVIEW){
                            TrackerOverviewScreen(
                                onNavigateToSearch = { mealName, day, month, year ->
                                    navController.navigate(
                                        Route.SEARCH + "/$mealName" +
                                                "/$day" +
                                                "/$month" +
                                                "/$year"
                                    )
                                }
                            )
                        }
                        composable(
                            route = Route.SEARCH + "/{mealName}/{dayOfMonth}/{month}/{year}",
                            arguments = listOf(
                                navArgument("mealName") {
                                    type = NavType.StringType
                                },
                                navArgument("dayOfMonth") {
                                    type = NavType.IntType
                                },
                                navArgument("month") {
                                    type = NavType.IntType
                                },
                                navArgument("year") {
                                    type = NavType.IntType
                                },
                            )
                        ) {
                            val mealName = it.arguments?.getString("mealName")!!
                            val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
                            val month = it.arguments?.getInt("month")!!
                            val year = it.arguments?.getInt("year")!!
                            SearchScreen(
                                scaffoldState = scaffoldState,
                                mealName = mealName,
                                dayOfMonth = dayOfMonth,
                                month = month,
                                year = year,
                                onNavigateUp = {
                                    navController.navigateUp()
                                }
                            )
                        }

                    }
                }

            }
        }
    }
}