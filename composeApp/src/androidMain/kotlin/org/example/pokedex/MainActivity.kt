package org.example.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.retainedComponent
import org.example.pokedex.di.initKoin
import org.example.pokedex.navigation.rootcomponent.DefaultRootComponent
import org.example.pokedex.navigation.rootcomponent.RootComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        initKoin {
//            androidLogger()
            androidContext(this@MainActivity)
        }

        val rootComponent: RootComponent = retainedComponent { DefaultRootComponent(it) }
        setContent { PokeDexApp(root = rootComponent) }
    }
}