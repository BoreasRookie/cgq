package com.boreas.persenter.PersenterImpl;

import com.boreas.framework.RxTimer;
import com.boreas.modle.LoginReceBean;
import com.boreas.persenter.IPersenters.IComPresenter;
import com.boreas.view.IViewInterface.IComViewInterface;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ComPresenter extends IComPresenter {
    private IComViewInterface iComViewInterface;

    public ComPresenter(IComViewInterface iComViewInterface) {
        super();
        this.iComViewInterface = iComViewInterface;
    }
    @Override
    public void saves(ArrayList<LoginReceBean.DataBean.Composition> compositions) {
        if (isNetWorkEnable()) {
            apiService.insertCompositions(compositions)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        if (data.getRetCode() == 0) {
                            new RxTimer().timer(300, number -> iComViewInterface.onAddCompositionsSuccess());
                        } else {
                            new RxTimer().timer(300, number -> iComViewInterface.onFailed(data.getMsg()));
                        }
                    }, e -> {
                        Logger.e(e.getMessage());
                        new RxTimer().timer(300, number -> iComViewInterface.onFailed(e.getMessage()));
                    });
        } else {
            this.noNetWork();
        }
    }
    @Override
    public void save(LoginReceBean.DataBean.Composition composition) {
        if (isNetWorkEnable()) {
            apiService.insertComposition(composition)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        if (data.getRetCode() == 0) {
                            new RxTimer().timer(300, number -> iComViewInterface.onSuccess());
                        } else {
                            new RxTimer().timer(300, number -> iComViewInterface.onFailed(data.getMsg()));
                        }
                    }, e -> {
                        Logger.e(e.getMessage());
                        new RxTimer().timer(300, number -> iComViewInterface.onFailed(e.getMessage()));
                    });
        } else {
            this.noNetWork();
        }
    }

    @Override
    public void update(LoginReceBean.DataBean.Composition composition) {
        if (isNetWorkEnable()) {
            apiService.updateComPositon(composition)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        if (data.getRetCode() == 0) {
                            new RxTimer().timer(300, number -> iComViewInterface.onUpdateSuccess());
                        } else {
                            new RxTimer().timer(300, number -> iComViewInterface.onFailed(data.getMsg()));
                        }
                    }, e -> {
                        Logger.e(e.getMessage());
                        new RxTimer().timer(300, number -> iComViewInterface.onFailed(e.getMessage()));
                    });
        } else {
            this.noNetWork();
        }
    }

    @Override
    public void noNetWork() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestory() {

    }
}
