/*
 * Copyright © Progmasters (QTC Kft.), 2018.
 * All rights reserved. No part or the whole of this Teaching Material (TM) may be reproduced, copied, distributed,
 * publicly performed, disseminated to the public, adapted or transmitted in any form or by any means, including
 * photocopying, recording, or other electronic or mechanical methods, without the prior written permission of QTC Kft.
 * This TM may only be used for the purposes of teaching exclusively by QTC Kft. and studying exclusively by QTC Kft.’s
 * students and for no other purposes by any parties other than QTC Kft.
 * This TM shall be kept confidential and shall not be made public or made available or disclosed to any unauthorized person.
 * Any dispute or claim arising out of the breach of these provisions shall be governed by and construed in accordance with the laws of Hungary.
 */

package com.progmasters.moovsmart.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {
    private List<CustomFieldError> fieldErrors = new ArrayList<>();

    public void addFieldError(String field, String message) {
        CustomFieldError error = new CustomFieldError(field, message);
        fieldErrors.add(error);
    }

    public List<CustomFieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<CustomFieldError> customFieldErrors) {
        this.fieldErrors = customFieldErrors;
    }

    private static class CustomFieldError {

        private String field;
        private String message;

        CustomFieldError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
