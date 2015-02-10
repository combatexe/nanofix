/*
 * Copyright 2015 LMAX Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lmax.nanofix.concurrent;

import java.util.concurrent.Semaphore;

public class ThreadBlocker implements Blocker
{
    private final Semaphore semaphore = new Semaphore(0);
    private volatile boolean paused;

    @Override
    public void mayWait()
    {
        while (paused)
        {
            semaphore.acquireUninterruptibly();
        }
    }

    public void pause()
    {
        paused = true;

    }

    public void resume()
    {
        paused = false;
        semaphore.release();
    }

}