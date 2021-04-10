package io.vasilenko.moviedb.ui.moviedetails

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.data.Actor
import io.vasilenko.moviedb.databinding.ItemActorBinding
import io.vasilenko.moviedb.ui.common.load

class ActorItem(
    private val actor: Actor
) : BindableItem<ItemActorBinding>() {

    override fun bind(binding: ItemActorBinding, position: Int) {
        with(binding) {
            actor.imagePath?.let { actorImage.load(it) }
            actorName.text = actor.name
        }
    }

    override fun getLayout() = R.layout.item_actor

    override fun initializeViewBinding(view: View): ItemActorBinding {
        return ItemActorBinding.bind(view)
    }
}
