package com.prochnow.ttsnotifications.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by prochnow on 03.07.15.
 */
abstract class NotificationType {

    @DatabaseField(generatedId = true) private long id;

    public NotificationType() {

    }


}
