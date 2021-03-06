/*
 * Mr.Mantou - On the importance of taste
 * Copyright (C) 2015-2016  XiNGRZ <xxx@oxo.ooo>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.zzq.zzq_collapsing_demo_master.utils;

import android.databinding.ObservableList;
import android.support.v4.view.PagerAdapter;

public class ObservableListPagerAdapterCallback<T extends ObservableList> extends ObservableList.OnListChangedCallback<T> {

    private final PagerAdapter adapter;

    public ObservableListPagerAdapterCallback(PagerAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onChanged(T sender) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeChanged(T sender, int positionStart, int itemCount) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeInserted(T sender, int positionStart, int itemCount) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeMoved(T sender, int fromPosition, int toPosition, int itemCount) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeRemoved(T sender, int positionStart, int itemCount) {
        adapter.notifyDataSetChanged();
    }

}
