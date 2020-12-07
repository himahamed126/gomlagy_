package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.AppSettingsResponse;

public interface AppSettingsInteractor {
    interface CallBack {

        void onAppSettingsLoaded(AppSettingsResponse appSettingsResponse);

        void onAppSettingsLoadedError();
    }
}
