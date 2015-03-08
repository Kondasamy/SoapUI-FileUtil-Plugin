package com.smartbear.soapui.fileutils.actions

/**
 * Created by Kondasamy J
 * Contact: Kondasamy.J@outlook.com
 */
import com.eviware.soapui.SoapUI
import com.eviware.soapui.impl.support.http.HttpRequestTestStep
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase
import com.eviware.soapui.model.testsuite.TestCase
import com.eviware.soapui.support.Tools
import com.eviware.soapui.support.UISupport
import com.eviware.soapui.support.action.support.AbstractSoapUIAction

class TestCaseSaveResponseAction extends AbstractSoapUIAction<WsdlTestCase>
{
    public TestCaseSaveResponseAction()
    {
        super("Save Response <Samy>", "Saves recent response to a file")
    }
    @Override
    void perform(WsdlTestCase wsdlTestCase, Object o)
    {
        SoapUI.log.info "Samy Testcase"
    }
}
