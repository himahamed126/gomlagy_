package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.PolicyContent;

public interface PolicyInteractor {
    interface CallBack {

        void onPolicyLoaded(PolicyContent policyContent);

        void onPolicyLoadError();
    }
}
