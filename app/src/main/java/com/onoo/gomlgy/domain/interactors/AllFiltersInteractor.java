package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.FiltersResponse;
import com.onoo.gomlgy.models.Category;
import com.onoo.gomlgy.models.FilterData;

import java.util.List;

public interface AllFiltersInteractor {
    interface CallBack {

        void onAllFiltersDownloaded(FilterData filterData);

        void onAllFiltersDownloadError();
    }
}
