package com.abualgait.msayed.myanimation

import android.app.Dialog
import android.content.res.TypedArray
import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.widget.NestedScrollView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FragmentBottomSheetDialogFull : BottomSheetDialogFragment() {
    private var mBehavior: BottomSheetBehavior<*>? = null


    @NonNull
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: BottomSheetDialog =
            super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        val view = View.inflate(
            getContext(),
            R.layout.fragment_bottom_sheet_dialog_full,
            null
        )
        dialog.setContentView(view)
        mBehavior = BottomSheetBehavior.from(view.parent as View)
        mBehavior?.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
        (view.findViewById(R.id.lyt_spacer) as View).minimumHeight = Tools.getScreenHeight()

        mBehavior?.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {


            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (BottomSheetBehavior.STATE_EXPANDED === newState) {

                }
                if (BottomSheetBehavior.STATE_COLLAPSED === newState) {

                }
                if (BottomSheetBehavior.STATE_HIDDEN === newState) {
                    dismiss()
                }
            }
        })
        coordinateMotion(view)
        return dialog
    }


    private fun coordinateMotion(view:View) {
        val appBarLayout: AppBarLayout = view.findViewById(R.id.appbar_layout)
        val motionLayout: MotionLayout = view.findViewById(R.id.motion_layout)
        val nestedScrlloView: NestedScrollView = view.findViewById(R.id.nestedScrlloView)

        val listener = AppBarLayout.OnOffsetChangedListener { unused, verticalOffset ->
            val seekPosition = -verticalOffset / appBarLayout.totalScrollRange.toFloat()
            motionLayout.progress = seekPosition
        }


 
        appBarLayout.addOnOffsetChangedListener(listener)
    }
    override fun onStart() {
        super.onStart()
        mBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun hideView(view: View?) {
        val params = view!!.layoutParams
        params.height = 0
        view.layoutParams = params
    }

    private fun showView(view: View?, size: Int) {
        val params = view!!.layoutParams
        params.height = size
        view.layoutParams = params
    }

    private val actionBarSize: Int
        get() {
            val styledAttributes: TypedArray =
                context!!.theme
                    .obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))
            return styledAttributes.getDimension(0, 0f).toInt()
        }
}