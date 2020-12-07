package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Models.PolicyContent;

public interface PolicyInteractor {
    interface CallBack {

        void onPolicyLoaded(PolicyContent policyContent);

        void onPolicyLoadError();
    }
}
