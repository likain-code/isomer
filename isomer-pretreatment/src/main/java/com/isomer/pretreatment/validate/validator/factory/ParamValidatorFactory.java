package com.isomer.pretreatment.validate.validator.factory;

import com.isomer.pretreatment.validate.enums.Mode;
import com.isomer.pretreatment.validate.validator.NamingParamValidator;
import com.isomer.pretreatment.validate.validator.ParamValidator;
import com.isomer.pretreatment.validate.validator.TypeParamValidator;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/2 13:00
 */
public class ParamValidatorFactory {

    public ParamValidator create(Mode mode) {
        if (mode.equals(Mode.NAMING)) {
            return new NamingParamValidator();
        } else if (mode.equals(Mode.TYPE)) {
            return new TypeParamValidator();
        }
        return null;
    }
}
