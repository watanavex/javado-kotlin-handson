package jp.watanave.githubsample.practice6

class View {
    companion object {
        fun createSample(): View {
            return View().apply {
                val child = View()
                this.addView(child)
            }
//            return View().also {
//                val child = View()
//                it.addView(child)
//            }
        }
    }

    var childView: View? = null; private set
    var isHidden: Boolean = false

    fun addView(child: View) {
        this.childView = child
    }

    fun hiddenGrandChild() {
        val childView = childView
//        childView?.apply {
//            childView.isHidden = true
//        }
        childView?.also {
            it.childView?.isHidden = true
        }
    }
}