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

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.target.Target;

import java.io.File;

import rx.Observable;

public class RxGlide {

    public static Observable<File> download(RequestManager rm, String url) {
        return download(rm, url, Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
    }

    public static Observable<File> download(RequestManager rm, String url, int width, int height) {
        return Observable.defer(() -> {
            try {
                return Observable.just(rm.load(url).downloadOnly(width, height).get());
            } catch (Exception e) {
                return Observable.error(e);
            }
        });
    }

}
