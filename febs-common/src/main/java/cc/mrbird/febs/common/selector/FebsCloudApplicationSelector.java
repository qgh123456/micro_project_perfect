package cc.mrbird.febs.common.selector;

import cc.mrbird.febs.common.configure.FebsAuthExceptionConfigure;
import cc.mrbird.febs.common.configure.FebsOAuth2FeignConfigure;
import cc.mrbird.febs.common.configure.FebsServerProtectConfigure;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/1/5 10:19
 */
public class FebsCloudApplicationSelector implements ImportSelector{
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                FebsAuthExceptionConfigure.class.getName(),
                FebsOAuth2FeignConfigure.class.getName(),
                FebsServerProtectConfigure.class.getName()
        };
    }

}
