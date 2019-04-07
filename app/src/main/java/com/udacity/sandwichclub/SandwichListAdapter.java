/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.udacity.sandwichclub;

import android.content.Context;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Shows how to implement a simple Adapter for a RecyclerView.
 * Demonstrates how to add a click handler for each item in the ViewHolder.
 */
public class SandwichListAdapter extends
        RecyclerView.Adapter<SandwichListAdapter.SandwichViewHolder> {

    private Context context;
    private final LinkedList<String> mSandwichList;
    private final LayoutInflater mInflater;

    class SandwichViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private Context context;
        public final TextView sandwichItemView;
        final SandwichListAdapter mAdapter;

        /**
         * Creates a new custom view holder to hold the view to display in
         * the RecyclerView.
         *
         * @param context
         * @param itemView The view in which to display the data.
         * @param adapter The adapter that manages the the data and views
         */
        public SandwichViewHolder(Context context, View itemView, SandwichListAdapter adapter) {
            super(itemView);
            this.context = context;
            sandwichItemView = itemView.findViewById(R.id.sandwich);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();

            // Use that to access the affected item in mWordList.
            String element = mSandwichList.get(mPosition);
            // Change the word in the mWordList.
            launchDetailActivity(mPosition);
            //mSandwichList.set(mPosition, "Clicked! " + element);
            // Notify the adapter, that the data has changed so it can
            // update the RecyclerView to display the data.
            //mAdapter.notifyDataSetChanged();
        }

        private void launchDetailActivity(int position) {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_POSITION, position);
            context.startActivity(intent);
        }
    }



    public SandwichListAdapter(Context context, LinkedList<String> sandwichList) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.mSandwichList = sandwichList;
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to
     * represent an item.
     *
     * This new ViewHolder should be constructed with a new View that can
     * represent the items of the given type. You can either create a new View
     * manually or inflate it from an XML layout file.
     *
     * The new ViewHolder will be used to display items of the adapter using
     * onBindViewHolder(ViewHolder, int, List). Since it will be reused to
     * display different items in the data set, it is a good idea to cache
     * references to sub views of the View to avoid unnecessary findViewById()
     * calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after
     *                 it is bound to an adapter position.
     * @param viewType The view type of the new View. @return A new ViewHolder
     *                 that holds a View of the given view type.
     */
    @Override
    public SandwichListAdapter.SandwichViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(R.layout.sandwichlist_item, parent, false);
        return new SandwichViewHolder(context, mItemView, this);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method should update the contents of the ViewHolder.itemView to
     * reflect the item at the given position.
     *
     * @param holder   The ViewHolder which should be updated to represent
     *                 the contents of the item at the given position in the
     *                 data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(SandwichListAdapter.SandwichViewHolder holder,
                                 int position) {
        // Retrieve the data for that position.
        String mCurrent = mSandwichList.get(position);
        // Add the data to the view holder.
        holder.sandwichItemView.setText(mCurrent);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mSandwichList.size();
    }
}
