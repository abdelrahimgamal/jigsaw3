package myapp.uitest.puzzlejigsaw.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import myapp.uitest.puzzlejigsaw.R


class NumbersAdapter(
    private val dataSet: List<Int>,
    private val listner: OnNumberInteract
) :
    RecyclerView.Adapter<NumbersAdapter.ViewHolder>() {
    private var context: Context? = null


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        val number: TextView


        init {
            number = view.findViewById(R.id.number)

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        context = viewGroup.context
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_number, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val number = dataSet.get(position)
        viewHolder.number.text = number.toString()

        viewHolder.itemView.setOnClickListener { listner.onNumberClicked(number) }
    }


    override fun getItemCount() = dataSet.size


    public interface OnNumberInteract {
        fun onNumberClicked(number: Int)

    }
}