package com.zsj.learndemo.aidl;
import android.os.IBinder;
import android.os.RemoteException;
import com.zsj.learndemo.IBinderPool;
import static com.zsj.learndemo.aidl.MyConstants.BINDER_COMPUTE;
import static com.zsj.learndemo.aidl.MyConstants.BINDER_SECURITY_CENTER;

public class BinderPoolImpl extends IBinderPool.Stub {
    @Override
    public IBinder queryBinder(int binderCode) throws RemoteException {
        IBinder binder = null;
        //根据不同的请求 Code 返回不同的 Binder 对象
        switch (binderCode) {
            case BINDER_SECURITY_CENTER:
                binder = new SecurityCenterImpl();
                break;
            case BINDER_COMPUTE:
                binder = new ComputeImpl();
                break;
            default:
                break;
        }
        return binder;
    }
}
