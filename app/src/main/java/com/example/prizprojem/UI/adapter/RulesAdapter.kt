package com.example.prizprojem.UI.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.prizprojem.R
import com.example.prizprojem.data.model.Rule
import com.example.prizprojem.databinding.RecyclerRowBinding

class RulesAdapter(val rulesList: List<Rule>) : RecyclerView.Adapter<RulesAdapter.RuleViewHolder>() {

    inner class RuleViewHolder(val recyclerviewRuleBinding: RecyclerRowBinding) : RecyclerView.ViewHolder(recyclerviewRuleBinding.root)
    // RecyclerRowBinding ' e tıklarsan seni layout'a gönderiyor, yani layouttan baika bir şey değil

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RuleViewHolder {
            return RuleViewHolder(
                DataBindingUtil.inflate<RecyclerRowBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.recycler_row,
                    parent,
                    false
                )
            )
    }

    override fun getItemCount(): Int {
        return rulesList.size
    }

    override fun onBindViewHolder(holder: RuleViewHolder, position: Int) {

        holder.recyclerviewRuleBinding.rule = rulesList.get(position)

    }

}
