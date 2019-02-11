package jp.watanave.githubsample.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jp.watanave.githubsample.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // adapterはリストデータとリストビューの橋渡しをするオブジェクトです
    private val adapter = RepositoryListAdapter()

    // この関数はリストビューの諸々の設定です
    private fun setupRecyclerView() {
        this.recyclerView.adapter = this.adapter
        this.recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        this.recyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                this,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )
        // TODO: [4] 2で定義したコールバックを登録する
        // コールバックの中では、ダイアログを出す
        /* こんな感じ
            val fragment = DetailDialogFragment.newInstance(repository.name, repository.owner.login, repository.description, repository.owner.avatarUrl)
            fragment.show(this.supportFragmentManager, DetailDialogFragment::class.simpleName)
         */
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)
        this.setupRecyclerView()

        // TODO: [1] 検索ボタンがタップされた時の動作を実装する
        /*
        ボタンには this.searchButton でアクセス可能。
        タップされた時の動作はボタンのsetOnClickListener関数で設定する
         */

        /*
        Apiのsearchメソッドでgithubリポジトリを検索する
        Apiのインスタンスはアプリケーションクラスから取得可能
        */

        /*
        検索文字列はthis.editTextから取得する
        */

        /*
        Apiリクエストをメインスレッドで実行するとランタイムエラーとなる
        バックグラウンドスレッドはKotlinのCoroutineを使うと便利
        GlobalScope.launch に続けてラムダを書くとバックグラウンドスレッドで実行される
        runOnUiThread に続けてラムダを書くとメインスレッドで実行される
         */

        /*
        Api#searchの戻り値はCall型
        Callのexecuteを呼び出すとApiリクエストが走る
        Call#executeの戻り値はResponse型
        Responseのbody#itemsからList<Repository>型の結果が取得できる
         */

        /*
        検索中はプログレスをくるくる回したい
        recyclerView.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
        こんな感じ
         */
    }
}