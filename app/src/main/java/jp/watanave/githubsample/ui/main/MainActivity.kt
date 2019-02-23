package jp.watanave.githubsample.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import jp.watanave.githubsample.App
import jp.watanave.githubsample.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // adapterはリストデータとリストビューの橋渡しをするオブジェクトです
    private val adapter = RepositoryListAdapter()

    // この関数はリストビュー(recyclerView)の諸々の設定です
    private fun setupRecyclerView() {
        this.recyclerView.adapter = this.adapter
        this.recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        this.recyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                this,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )
        // TODO: [6] 2で定義したコールバックを登録する
        // コールバックの中では、ダイアログを出す
        /* こんな感じ
            val fragment = DetailDialogFragment.newInstance(repository.name, repository.owner.login, repository.description, repository.owner.avatarUrl)
            fragment.show(this.supportFragmentManager, DetailDialogFragment::class.simpleName)
         */
    }

    // 画面が作成された時にシステムから呼び出されるコールバックです
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)
        this.setupRecyclerView()

        // TODO: [1] 検索ボタンがタップされた時の動作を実装する
        /*
        ボタンには this.searchButton でアクセス可能。
        タップされた時の動作はボタンのsetOnClickListener関数で設定する
        ボタンがタップされたらsearchRepositoryメソッドを呼びましょう
        検索文字列はthis.editTextから取得します
         */
    }

    private fun searchRepository(searchWord: String) {
        // 検索が開始する前にプログレスをくるくる回す
        recyclerView.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE

        // Kotlin coroutineを使ってネットワーク通信中にスレッドをブロックさせないようにする
        GlobalScope.launch {
            val api = App.instance.api

            // TODO: [2] 検索ボタンがタップされた時の動作を実装する
            /*
            Apiのsearchメソッドでgithubリポジトリを検索する
            検索結果をadapterに渡しましょう
             */
            runOnUiThread {
                adapter.refreshData(emptyList())
            }

            // TODO: [3] 検索が完了したらprogressBarを隠してrecyclerViewを表示しましょう
        }
    }
}