package com.top.score.convert;

import com.top.score.domain.BizResult;
import com.top.score.response.BaseResponse;

public abstract class BaseConverter {

	public abstract BaseResponse convertToResponse(BizResult bizRes);
}
