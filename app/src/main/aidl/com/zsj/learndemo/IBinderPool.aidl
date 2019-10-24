// IBinderPool.aidl
package com.zsj.learndemo;

// Declare any non-default types here with import statements

interface IBinderPool {
    IBinder queryBinder(int binderCode);
}
