package com.demo.Test;

import com.demo.model.common.User;
import com.demo.model.testcase.PictureBean;
import com.demo.model.testcase.TestCase;
import com.demo.model.testcase.TestCaseContent;
import com.demo.model.testcase.caseDetail;
import com.demo.service.common.UserService;
import com.demo.service.testcase.TestCaseDetailService;
import com.demo.service.testcase.TestCaseService;
import com.demo.util.Generator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class DataBaseOperator {
    ApplicationContext ac = null;
    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml", "spring-hibernate.xml"});
    }

   @Test
   public void TestHibernateUser(){
       TestCaseService testCaseService = (TestCaseService)ac.getBean("testCaseService");
       TestCaseDetailService testCaseDetailService =(TestCaseDetailService) ac.getBean("testCaseDetailService");
       List<TestCase> testCasesList = testCaseService.getScrollData().getDatas();
       for(TestCase temp:testCasesList){

           if(temp.getStatus().equals("Draft")){
               caseDetail detail = new caseDetail();
               List<TestCaseContent> list = testCaseService.findCaseContentByCaseKey(temp.getCaseinstkey());
               detail.setCaseId(temp.getCaseinstkey());
               detail.setCaseDetailId(Generator.generatorID());
               detail.setCreatedate(new Date());
               detail.setUpdatedate(new Date());
               detail.setCreateby("Chill");
               detail.setUpdateby("Chill");
               testCaseDetailService.saveOrUpdate(detail);
           }
       }
      // List<TestCaseContent> list = testCaseService.findCaseContentByCaseKey();
   }

   @Test
    public void ChangeCaseContent(){
       TestCaseService testCaseService = (TestCaseService)ac.getBean("testCaseService");

       TestCaseDetailService testCaseDetailService =(TestCaseDetailService) ac.getBean("testCaseDetailService");
       //List<TestCase> testCasesList = testCaseService.getScrollData().getDatas();
      List<caseDetail> caseDetailList = testCaseDetailService.getScrollData().getDatas();
      for(caseDetail tp :caseDetailList){
          String content = "<table class=\"table table-bordered\">\n" +
                  "<tbody>\n" +
                  "<tr>\n" +
                  "<td>language Support</td>\n" +
                  "<td>Language Comments</td>\n" +
                  "</tr>\n" +
                  "</tbody>\n" +
                  "\n" +
                  "<tbody>\n" +
                  "<tr>\n" +
                  "<td>\n" +
                  "<table class=\"table table-condensed table-bordered\">\n" +
                  "<tbody>\n" +
                  "<tr>\n" +
                  "<td width=\"30\">OS</td>                                   \n" +
                  "<td width=\"40\">RE</td>\n" +
                  "<td width=\"40\">FR</td>\n" +
                  "<td width=\"40\">GR</td>\n" +
                  "<td width=\"40\">IT</td>\n" +
                  "<td width=\"40\">SP</td>\n" +
                  "<td width=\"40\">NO</td>\n" +
                  "<td width=\"40\">HU</td>\n" +
                  "<td width=\"40\">FI</td>\n" +
                  "<td width=\"40\">RU</td>\n" +
                  "<td width=\"40\">NL</td>\n" +
                  "<td width=\"40\">BR</td>\n" +
                  "<td width=\"40\">PO</td>\n" +
                  "<td width=\"40\">AR</td>\n" +
                  "<td width=\"40\">CZ</td>\n" +
                  "<td width=\"40\">GK</td>                              \n" +
                  "</tr>\n" +
                  "\n" +
                  "<tr>\n" +
                  "<td>Language</td>\n" +
                  "<td>RE</td>\n" +
                  "<td>FR</td>\n" +
                  "<td>GR</td>\n" +
                  "<td>IT</td>\n" +
                  "<td>SP</td>\n" +
                  "<td>NO</td>\n" +
                  "<td>HU</td>\n" +
                  "<td>FI</td>\n" +
                  "<td>RU</td>\n" +
                  "<td>NL</td>\n" +
                  "<td>BR</td>\n" +
                  "<td>PO</td>\n" +
                  "<td>AR</td>\n" +
                  "<td>CZ</td>\n" +
                  "<td>GK</td>\n" +
                  "</tr>\n" +
                  "\n" +
                  "<tr>\n" +
                  "<td width=\"30\">OS</td>                             \n" +
                  "<td width=\"40\">HB</td>\n" +
                  "<td width=\"40\">SV</td>\n" +
                  "<td width=\"40\">PL</td>\n" +
                  "<td width=\"40\">DK</td>\n" +
                  "<td width=\"40\">SL</td>\n" +
                  "<td width=\"40\">TR</td>\n" +
                  "<td width=\"40\">RO</td>\n" +
                  "<td width=\"40\">SR</td>\n" +
                  "<td width=\"40\">SK</td>\n" +
                  "<td width=\"40\">KR</td>\n" +
                  "<td width=\"40\">CS</td>\n" +
                  "<td width=\"40\">CT</td>\n" +
                  "<td width=\"40\">HK</td>\n" +
                  "<td width=\"40\">JP</td>\n" +
                  "<td width=\"40\">NE</td>\n" +
                  "</tr>\n" +
                  "\n" +
                  "<tr>\n" +
                  "<td>Language</td>\n" +
                  "<td>HB</td>\n" +
                  "<td>SV</td>\n" +
                  "<td>PL</td>\n" +
                  "<td>DK</td>\n" +
                  "<td>SL</td>\n" +
                  "<td>TR</td>\n" +
                  "<td>RO</td>\n" +
                  "<td>SR</td>\n" +
                  "<td>SK</td>\n" +
                  "<td>KR</td>\n" +
                  "<td>CS</td>\n" +
                  "<td>CT</td>\n" +
                  "<td>HK</td>\n" +
                  "<td>JP</td>\n" +
                  "<td>NE</td>\n" +
                  "</tr>                       \n" +
                  "</tbody>\n" +
                  "</table>\n" +
                  "</td>\n" +
                  "\n" +
                  "<td></td>\n" +
                  "</tr>\n" +
                  "</tbody>\n" +
                  "</table>\n" +
                  "\n" +
                  "<table class=\"table table-bordered\">\n" +
                  "<tbody>\n" +
                  "<tr>\n" +
                  "<td> HW requirements</td>\n" +
                  "<td> HW Comments</td>\n" +
                  "</tr>\n" +
                  "<tr>\n" +
                  "<td></td>\n" +
                  "<td></td>\n" +
                  "</tr>\n" +
                  "</tbody>\n" +
                  "</table>\n" +
                  "\n" +
                  "<legend>3 - Test Items :</legend>\n" +
                  "            \n" +
                  "<li>P1 ?MUST executing in every testing</li>\n" +
                  "<li>P2 ?US&amp;Localized APP Testing</li>\n" +
                  "<li>P3 ?Function Test</li>\n" +
                  "<br>\n" +
                  "<li>Total Time: mins</li>\n" +
                  "\n" +
                  "<table class=\"table table-bordered\">\n" +
                  "\n" +
                  "<thead>\n" +
                  "<tr>\n" +
                  "<th width=\"10%\">Level</th>\n" +
                  "<th width=\"20%\">Test Item</th>\n" +
                  "<th width=\"20%\">Test Steps/Description</th>\n" +
                  "<th width=\"20%\">Expect Results</th>\n" +
                  "<th width=\"15%\">Pictures</th>\n" +
                  "<th width=\"15%\">Comments</th>\n" +
                  "</tr>\n" +
                  "</thead>\n" +
                  "\n" +
                  "<tbody>\n" +
                  "\n";
          List<TestCaseContent> list = testCaseService.findCaseContentByCaseKey(tp.getCaseId());
          for(TestCaseContent caseContent :list){
              content += "<tr>";
              content +="<td>"+caseContent.getCaselevel()+"</td>\n";
              content +="<td>"+caseContent.getTestitem()+"</td>\n";
              content += "<td>" + caseContent.getTeststep()+"</td>\n";
              content += "<td>" + caseContent.getTestresult() + "</td>\n";
              List<PictureBean> picList = testCaseService.findPictureByCaseContentkey(caseContent.getCasecontentinstkey());
              if(picList.size()>0){
                for(PictureBean pic : picList){
                    String picPath = pic.getFilepath().replace("D:\\CTDDataBase\\CasePics\\","http://localhost:8080/profile/TestCase/");
                    picPath =picPath.replace("\\","/");
                    String fileName = pic.getFilename();
                    content += "<td><p><img src=\""+picPath +"\" data-filename=\""+fileName +"\" style=\"width: 100%; float: none;\" class=\"\"><br></p></td>";
                }
              }else {
                  content += "<td></td>\n";
              }
              content += "<td>" + caseContent.getComment() + "</td>\n";
              content += "</tr>";
          }
          content += "</tbody>\n" +
                  "</table>";
            tp.setCaseDetailContent(content);
            testCaseDetailService.update(tp);
      }

   }
}
