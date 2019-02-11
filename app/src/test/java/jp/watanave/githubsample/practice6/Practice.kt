package jp.watanave.githubsample.practice6

class View {
    companion object {
        fun createSample(): View {
            val view = View()
            val child = View()
            view.addView(child)
            return view
        }
    }

    var childView: View? = null; private set
    var isHidden: Boolean = false

    fun addView(child: View) {
        this.childView = child
    }

    fun hiddenGrandChild() {
        val childView = childView
        childView?.apply {
            childView.isHidden = true
        }
    }
}