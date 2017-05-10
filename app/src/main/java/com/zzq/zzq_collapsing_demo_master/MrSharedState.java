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

package com.zzq.zzq_collapsing_demo_master;

import android.databinding.ObservableArrayList;

import com.zzq.zzq_collapsing_demo_master.entity.Image;


public class MrSharedState {

    private static MrSharedState instance;

    private final ObservableArrayList<Image> images = new ObservableArrayList<>();

    public static MrSharedState getInstance() {
        return instance;
    }

    static void createInstance() {
        instance = new MrSharedState();
    }

    public ObservableArrayList<Image> getImages() {
        return images;
    }

}
