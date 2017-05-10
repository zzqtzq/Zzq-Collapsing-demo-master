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

package com.zzq.zzq_collapsing_demo_master.rx;

import java.util.List;

import rx.functions.Action1;

public class RxList {

    public static <E> Action1<List<E>> appendTo(List<E> list) {
        return list::addAll;
    }

    public static <E> Action1<List<E>> prependTo(List<E> list) {
        return items -> list.addAll(0, items);
    }

}
