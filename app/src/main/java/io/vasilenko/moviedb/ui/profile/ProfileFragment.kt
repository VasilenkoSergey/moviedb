package io.vasilenko.moviedb.ui.profile

import android.os.Bundle
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import io.vasilenko.moviedb.R
import io.vasilenko.moviedb.databinding.FragmentProfileBinding
import io.vasilenko.moviedb.ui.common.viewBinding
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding { FragmentProfileBinding.bind(it) }

    private lateinit var profileTabLayoutTitles: Array<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        profileTabLayoutTitles = resources.getStringArray(R.array.tab_titles)

        val profileAdapter = ProfileAdapter(
            this,
            profileTabLayoutTitles.size
        )
        with(binding) {
            Picasso.get()
                .load(R.drawable.ic_avatar)
                .transform(CropCircleTransformation())
                .placeholder(R.drawable.ic_avatar)
                .into(avatar)

            doppelgangerViewPager.adapter = profileAdapter

            doppelgangerViewPager.registerOnPageChangeCallback(profilePageChangeCallback)

            TabLayoutMediator(tabLayout, doppelgangerViewPager) { tab, position ->

                val title = profileTabLayoutTitles[position]
                val parts = profileTabLayoutTitles[position].split(" ")
                val number = parts[0]
                val spannableStringTitle = SpannableString(title)
                spannableStringTitle.setSpan(RelativeSizeSpan(2f), 0, number.count(), 0)

                tab.text = spannableStringTitle
            }.attach()
        }
    }

    private var profilePageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            Toast.makeText(
                requireContext(),
                "Selected position: $position",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
