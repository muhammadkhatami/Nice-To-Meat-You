package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.adapter.NoteAdapter
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Note
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Recipe
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_detail_recipe.*
import kotlinx.android.synthetic.main.activity_detail_recipe.recipeRV
import java.lang.Thread.sleep

class DetailRecipeActivity : AppCompatActivity() {

    private var continueTicking = false
    private var countDown = 20

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter

    val br: BroadcastReceiver = MyBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_recipe)

        recipeRV.layoutManager = LinearLayoutManager(this)
        noteAdapter = NoteAdapter(this) { recipe, i ->

        }
        recipeRV.adapter = noteAdapter

        val thisRecipe = intent.getParcelableExtra<Recipe>("thisRecipe")

        val timerText: TextView = findViewById(R.id.timerText)
        timerText.text = thisRecipe.time.toString()
        countDown = thisRecipe.time

        val recipeTitle: TextView = findViewById(R.id.recipeTitle)
        recipeTitle.text = thisRecipe.recipe_title

        val buttonStartTimer: Button = findViewById(R.id.startTimerButton)
        buttonStartTimer.setOnClickListener{
            startTimer()
        }

        val createNoteButton: Button = findViewById(R.id.createNoteButton)
        createNoteButton.setOnClickListener{
            showAlertDialogAdd(thisRecipe)
        }

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.getNotesByRecipeId(thisRecipe.id.toString().toInt())?.observe(this, Observer {
            noteAdapter.setNotes(it)
        })
    }

    private fun showAlertDialogAdd(thisRecipe: Recipe) {
        val alert = AlertDialog.Builder(this)

        val notesText = EditText(applicationContext)
        notesText.hint = "notes"

        alert.setTitle("Note")
        alert.setView(notesText)

        val layout = LinearLayout(applicationContext)
        layout.orientation = LinearLayout.VERTICAL

        val textBox = EditText(applicationContext)
        textBox.hint = "notes"
        layout.addView(textBox) // Notice this is an start method
        alert.setView(layout) // Again this is a set method, not start

        alert.setPositiveButton("Save") { dialog, _ ->
            noteViewModel.insertNote(
                Note(
                    text = textBox.text.toString(),
                    recipeId = thisRecipe.id.toString().toInt()
                )
            )
            dialog.dismiss()
        }
        alert.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        alert.show()
    }

    private fun startTimer() {
        val startTimerButton : Button = findViewById<Button>(R.id.startTimerButton)

        continueTicking = true

        Thread(Runnable {
            while (countDown > 0 && continueTicking) {
                sleep(1000)
                countDown -= 1
                this@DetailRecipeActivity.runOnUiThread(java.lang.Runnable {
                    this.timerText.text = "${countDown.toString()}"
                })
            }
            if (countDown == 0) {
                this@DetailRecipeActivity.runOnUiThread(java.lang.Runnable {
                    this.timerText.text = "Timeout"
                    val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
                        addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
                    }
                    registerReceiver(br, filter)
                })
            }
        }).start()
    }

}