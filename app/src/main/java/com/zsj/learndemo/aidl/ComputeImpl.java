package com.zsj.learndemo.aidl;

import android.os.RemoteException;

import com.zsj.learndemo.ICompute;

public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
