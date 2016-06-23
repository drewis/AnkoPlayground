package org.opensilk.playground

import android.content.res.TypedArray
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.*
import java.util.*

/**
 * foo
 */
internal class SimpleListItemFactory: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            include(R.layout.recycler_list_item)
        }
    }
    companion object {
        fun create(parent: ViewGroup): View {
            return SimpleListItemFactory().createView(AnkoContext.create(parent.context, parent))
        }
    }
}

/**
 * foo
 */
class SimpleListItem(itemView: View): RecyclerView.ViewHolder(itemView) {

    val textView: TextView = itemView.find(R.id.simple_text)
    val random: Random = Random();

    fun bind(text: String) {
        val colors: TypedArray = itemView.context.resources.obtainTypedArray(R.array.tile_colors);
        try {
            textView.text = text
            textView.textColor = colors.getColor(random.nextInt(colors.length()), 0)
        } finally {
            colors.recycle()
        }
    }
}

/**
 * foo
 */
class SimpleRecyclerAdapter(private val items: Array<String>): RecyclerView.Adapter<SimpleListItem>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SimpleListItem? {
        return SimpleListItem(SimpleListItemFactory.create(parent!!))
    }

    override fun onBindViewHolder(holder: SimpleListItem?, position: Int) {
        holder!!.bind(items[position])
    }

}