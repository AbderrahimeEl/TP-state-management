package com.example.gestion_etat

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gestion_etats.ui.theme.Gestion_etatsTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Gestion_etatsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    SansEtat()
//                    AvecEtat()
//                    AvecEtatObserve()
//                    AvecEtatObservePermanente()
                    EtatViewModel()
                }
            }
        }
    }
}

class MaViewModel : ViewModel() {
    val _cpt = MutableStateFlow(0)
    val cpt: StateFlow<Int> get() = _cpt

    fun increment() {
        _cpt.value++
    }
}

@Composable
fun EtatViewModel() {
    val model: MaViewModel = viewModel()

    val i by model.cpt.collectAsState()
    var txt by rememberSaveable { mutableStateOf("Cliquez ici") }

    Button(onClick = {
        model.increment()
        txt = "C'est bon vous avez cliqué ici $i"
    }) {
        Text(text = txt)
    }
}

@Composable
fun SansEtat() {
    Button(
        onClick = { }) {
        Text(text = "Cliquez Ici")
    }
}


@Composable
fun AvecEtat() {
    var txt = "Cliquez ici"
    Log.d("Tag", "Avant Clique le texte est : $txt")
    Button(onClick = {
        txt = "C 'est bon vous avez Cliquez ici"
        Log.d("Tag", "Après Clique le texte est : $txt")
    }) {
        Text(text = txt)
    }
}

@Composable
fun AvecEtatObserve() {
    var txt by remember { mutableStateOf("Cliquez ici") }
    var i: Int = 1
    Button(onClick = {
        txt = "C'est bon vous avez cliqué ici $i"
        i++
    }) {
        Text(text = txt)
    }
}

@Composable
fun AvecEtatObservePermanente() {
    var txt by rememberSaveable { mutableStateOf("Cliquez ici") }
    var i by rememberSaveable { mutableStateOf(0) }
    Button(onClick = {
        i++
        txt = "C'est bon vous avez cliqué ici $i"
    }) {
        Text(text = txt)
    }
}