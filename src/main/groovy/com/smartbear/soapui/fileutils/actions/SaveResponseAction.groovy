package com.smartbear.soapui.fileutils.actions

import com.eviware.soapui.SoapUI
import com.eviware.soapui.impl.support.http.HttpRequestTestStep
import com.eviware.soapui.support.Tools
import com.eviware.soapui.support.UISupport
import com.eviware.soapui.support.action.support.AbstractSoapUIAction

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class SaveResponseAction extends AbstractSoapUIAction<HttpRequestTestStep>{

    public SaveResponseAction()
    {
        super( "Save Response <Samy>", "Saves recent response to a file" );
    }


    @Override
    void perform(HttpRequestTestStep httpTestRequestStep, Object o)
    {
        //Get the response of current TestStep
        def response = httpTestRequestStep.httpRequest.response
        /*Check if response is null/ means teststep not ran*/
        if( response == null )
        {
            UISupport.showInfoMessage( "Missing Response for TestStep ");
            return
        }
        /*Check if null response returned post successful run of the teststep*/
        def data = response.rawResponseData
        def data1 = httpTestRequestStep.httpRequest.getResponse().getContentAsXml()
        if( data == null || data.length == 0 )
        {
            UISupport.showInfoMessage( "Empty Response data for TestStep" );
            return
        }
        /*If the data is available, save to a file in User's current directory*/
        else
        {
            def today= new Date() //represents the date and time when it is created
            //SoapUI.log today.format("yyyyMMdd-HH:mm:ss")
            String today1 = today.format("yyyyMMdd-HH:mm:ss")
            //SoapUI.log today1
            String result = today1.replaceAll(":", "");
            //SoapUI.log result
            String result1 = result.replaceAll("-", "");
            SoapUI.log.info "Refined Date is : " + result1
            def tstName = httpTestRequestStep.testStep.getName()
            def tcName = httpTestRequestStep.testCase.getName()
            def tsName = httpTestRequestStep.testCase.testSuite.getName()
            def projName = httpTestRequestStep.testCase.testSuite.project.name
            String fileName = tcName+"__" +tstName+ "__" + result1 +".txt"
            SoapUI.log.info "File Name is :: " +fileName

            String fileName1 = fileName.replaceAll("/", "-");
            String fileName2 = fileName1.replaceAll(";","-");
            String fileName3 = fileName2.replaceAll(":","-");
            String fileName4 = fileName3.replaceAll(",","-");
            String fileName5 = fileName4.replaceAll("\\?","-");

//            def projectPath = httpTestRequestStep.testCase.testSuite.project.getPath()
//            SoapUI.log.info projectPath
//            def projectPath1 = httpTestRequestStep.testCase.testSuite.getProject().path
//            def projectPath1 = httpTestRequestStep.testCase.testSuite.project.getPropertyValue()
//            SoapUI.log.info projectPath1
//            def fullPath = projectPath +"/" + fileName5
//            SoapUI.log.info "Full Path is :: " + fullPath
            def mainDir = System.getProperty('user.home')
            SoapUI.log.info "User's current Directory is : " + mainDir
            def SubDir = mainDir+"\\"+projName
            def SubDir1 = new File(SubDir)
            if(!SubDir1.exists())
            {
                def file = new File(SubDir1,fileName5)//hardcoded
                if(!file.exists())
                    file.append System.getProperty("line.separator") + "Oper 1" + data1
                else
                    file << System.getProperty("line.separator") + "Oper 2" + data1
            }
            else
            {
                SubDir1.mkdir()
                def file = new File(SubDir1,fileName5)
                file << System.getProperty("line.separator") + "Oper 3" + data1
            }
            UISupport.showInfoMessage("File Successfully saved!!","Success!!!")


        }
    }
}
