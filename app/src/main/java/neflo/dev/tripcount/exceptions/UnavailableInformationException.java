package neflo.dev.tripcount.exceptions;

import android.content.res.Resources;

import neflo.dev.tripcount.R;

public class UnavailableInformationException extends RuntimeException {
    public UnavailableInformationException(Resources resources) {
        super(resources.getString(R.string.unavailable_information));
    }
}
