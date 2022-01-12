package com.AlkemyChallange.Disney.services;

import static com.AlkemyChallange.Disney.security.SecurityConstants.SENDGRID_API_KEY;
import java.io.IOException;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void send(String destinatario) throws IOException {
        Email from = new Email("disneymundode@gmail.com");
        String subject = "Bienvenido al mundo de Disney!";
        Email to = new Email(destinatario);
        Content content = new Content("text/html", emailTemplate());

        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(SENDGRID_API_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }

    public String emailTemplate() {
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n"
                + "<html data-editor-version=\"2\" class=\"sg-campaigns\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "    <head>\n"
                + "      <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n"
                + "      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1\">\n"
                + "      <!--[if !mso]><!-->\n"
                + "      <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\">\n"
                + "      <!--<![endif]-->\n"
                + "      <!--[if (gte mso 9)|(IE)]>\n"
                + "      <xml>\n"
                + "        <o:OfficeDocumentSettings>\n"
                + "          <o:AllowPNG/>\n"
                + "          <o:PixelsPerInch>96</o:PixelsPerInch>\n"
                + "        </o:OfficeDocumentSettings>\n"
                + "      </xml>\n"
                + "      <![endif]-->\n"
                + "      <!--[if (gte mso 9)|(IE)]>\n"
                + "  <style type=\"text/css\">\n"
                + "    body {width: 600px;margin: 0 auto;}\n"
                + "    table {border-collapse: collapse;}\n"
                + "    table, td {mso-table-lspace: 0pt;mso-table-rspace: 0pt;}\n"
                + "    img {-ms-interpolation-mode: bicubic;}\n"
                + "  </style>\n"
                + "<![endif]-->\n"
                + "      <style type=\"text/css\">\n"
                + "    body, p, div {\n"
                + "      font-family: inherit;\n"
                + "      font-size: 14px;\n"
                + "    }\n"
                + "    body {\n"
                + "      color: #000000;\n"
                + "    }\n"
                + "    body a {\n"
                + "      color: #1188E6;\n"
                + "      text-decoration: none;\n"
                + "    }\n"
                + "    p { margin: 0; padding: 0; }\n"
                + "    table.wrapper {\n"
                + "      width:100% !important;\n"
                + "      table-layout: fixed;\n"
                + "      -webkit-font-smoothing: antialiased;\n"
                + "      -webkit-text-size-adjust: 100%;\n"
                + "      -moz-text-size-adjust: 100%;\n"
                + "      -ms-text-size-adjust: 100%;\n"
                + "    }\n"
                + "    img.max-width {\n"
                + "      max-width: 100% !important;\n"
                + "    }\n"
                + "    .column.of-2 {\n"
                + "      width: 50%;\n"
                + "    }\n"
                + "    .column.of-3 {\n"
                + "      width: 33.333%;\n"
                + "    }\n"
                + "    .column.of-4 {\n"
                + "      width: 25%;\n"
                + "    }\n"
                + "    ul ul ul ul  {\n"
                + "      list-style-type: disc !important;\n"
                + "    }\n"
                + "    ol ol {\n"
                + "      list-style-type: lower-roman !important;\n"
                + "    }\n"
                + "    ol ol ol {\n"
                + "      list-style-type: lower-latin !important;\n"
                + "    }\n"
                + "    ol ol ol ol {\n"
                + "      list-style-type: decimal !important;\n"
                + "    }\n"
                + "    @media screen and (max-width:480px) {\n"
                + "      .preheader .rightColumnContent,\n"
                + "      .footer .rightColumnContent {\n"
                + "        text-align: left !important;\n"
                + "      }\n"
                + "      .preheader .rightColumnContent div,\n"
                + "      .preheader .rightColumnContent span,\n"
                + "      .footer .rightColumnContent div,\n"
                + "      .footer .rightColumnContent span {\n"
                + "        text-align: left !important;\n"
                + "      }\n"
                + "      .preheader .rightColumnContent,\n"
                + "      .preheader .leftColumnContent {\n"
                + "        font-size: 80% !important;\n"
                + "        padding: 5px 0;\n"
                + "      }\n"
                + "      table.wrapper-mobile {\n"
                + "        width: 100% !important;\n"
                + "        table-layout: fixed;\n"
                + "      }\n"
                + "      img.max-width {\n"
                + "        height: auto !important;\n"
                + "        max-width: 100% !important;\n"
                + "      }\n"
                + "      a.bulletproof-button {\n"
                + "        display: block !important;\n"
                + "        width: auto !important;\n"
                + "        font-size: 80%;\n"
                + "        padding-left: 0 !important;\n"
                + "        padding-right: 0 !important;\n"
                + "      }\n"
                + "      .columns {\n"
                + "        width: 100% !important;\n"
                + "      }\n"
                + "      .column {\n"
                + "        display: block !important;\n"
                + "        width: 100% !important;\n"
                + "        padding-left: 0 !important;\n"
                + "        padding-right: 0 !important;\n"
                + "        margin-left: 0 !important;\n"
                + "        margin-right: 0 !important;\n"
                + "      }\n"
                + "      .social-icon-column {\n"
                + "        display: inline-block !important;\n"
                + "      }\n"
                + "    }\n"
                + "  </style>\n"
                + "      <!--user entered Head Start--><link href=\"https://fonts.googleapis.com/css?family=Fira+Sans&display=swap\" rel=\"stylesheet\"><style>\n"
                + "    body {font-family: 'Fira Sans', sans-serif;}\n"
                + "</style><!--End Head user entered-->\n"
                + "    </head>\n"
                + "    <body>\n"
                + "      <center class=\"wrapper\" data-link-color=\"#1188E6\" data-body-style=\"font-size:14px; font-family:inherit; color:#000000; background-color:#eae3da;\">\n"
                + "        <div class=\"webkit\">\n"
                + "          <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" class=\"wrapper\" bgcolor=\"#eae3da\">\n"
                + "            <tr>\n"
                + "              <td valign=\"top\" bgcolor=\"#eae3da\" width=\"100%\">\n"
                + "                <table width=\"100%\" role=\"content-container\" class=\"outer\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                + "                  <tr>\n"
                + "                    <td width=\"100%\">\n"
                + "                      <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                + "                        <tr>\n"
                + "                          <td>\n"
                + "                            <!--[if mso]>\n"
                + "    <center>\n"
                + "    <table><tr><td width=\"600\">\n"
                + "  <![endif]-->\n"
                + "                                    <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:100%; max-width:600px;\" align=\"center\">\n"
                + "                                      <tr>\n"
                + "                                        <td role=\"modules-container\" style=\"padding:0px 0px 0px 0px; color:#000000; text-align:left;\" bgcolor=\"#FFFFFF\" width=\"100%\" align=\"left\"><table class=\"module preheader preheader-hide\" role=\"module\" data-type=\"preheader\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"display: none !important; mso-hide: all; visibility: hidden; opacity: 0; color: transparent; height: 0; width: 0;\">\n"
                + "    <tr>\n"
                + "      <td role=\"module-content\">\n"
                + "        <p>El registro fue exitoso....</p>\n"
                + "      </td>\n"
                + "    </tr>\n"
                + "  </table><table class=\"module\" role=\"module\" data-type=\"text\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout: fixed;\" data-muid=\"e655eeb4-f0e5-4f1f-b296-27a035f0405b\" data-mc-module-version=\"2019-10-22\">\n"
                + "    <tbody>\n"
                + "      <tr>\n"
                + "        <td style=\"padding:30px 40px 18px 40px; line-height:50px; text-align:inherit; background-color:#00004e;\" height=\"100%\" valign=\"top\" bgcolor=\"#00004e\" role=\"module-content\"><div><h2 style=\"text-align: center; font-family: inherit\"><span style=\"font-size: 50px; font-family: inherit; color: #a9a6a6\"><strong>Gracias por tu registro en</strong></span><span style=\"color: #fa6400; font-size: 50px; font-family: inherit\"><strong> </strong></span><span style=\"font-family: &quot;lucida sans unicode&quot;, &quot;lucida grande&quot;, sans-serif; font-size: 60px; color: #ffffff\"><strong>Disney</strong></span><span style=\"font-family: &quot;lucida sans unicode&quot;, &quot;lucida grande&quot;, sans-serif; font-size: 60px; color: #a9a6a6\"><strong>&nbsp;</strong></span></h2><div></div></div></td>\n"
                + "      </tr>\n"
                + "    </tbody>\n"
                + "  </table><table class=\"module\" role=\"module\" data-type=\"text\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout: fixed;\" data-muid=\"75c8e846-3c79-4ea1-80e8-95c6ca4763d4\" data-mc-module-version=\"2019-10-22\">\n"
                + "    <tbody>\n"
                + "      <tr>\n"
                + "        <td style=\"padding:18px 0px 18px 0px; line-height:22px; text-align:inherit; background-color:#00004E;\" height=\"100%\" valign=\"top\" bgcolor=\"#00004E\" role=\"module-content\"><div><div style=\"font-family: inherit; text-align: center\"><span style=\"color: #ffffff; font-size: 24px\">Que disfrutes la aplicación!</span></div>\n"
                + "<div style=\"font-family: inherit; text-align: center\"><br></div><div></div></div></td>\n"
                + "      </tr>\n"
                + "    </tbody>\n"
                + "  </table><table class=\"wrapper\" role=\"module\" data-type=\"image\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout: fixed;\" data-muid=\"237874b6-8020-49eb-9f7b-7a4fde338ffe\">\n"
                + "    <tbody>\n"
                + "      <tr>\n"
                + "        <td style=\"font-size:6px; line-height:10px; padding:0px 0px 0px 0px;\" valign=\"top\" align=\"center\">\n"
                + "          <img class=\"max-width\" border=\"0\" style=\"display:block; color:#000000; text-decoration:none; font-family:Helvetica, arial, sans-serif; font-size:16px; max-width:100% !important; width:100%; height:auto !important;\" width=\"600\" alt=\"\" data-proportionally-constrained=\"true\" data-responsive=\"true\" src=\"http://cdn.mcauto-images-production.sendgrid.net/39420f75179498a1/f07f9329-3891-464e-aea8-6eb39112b138/900x900.jpg\">\n"
                + "        </td>\n"
                + "      </tr>\n"
                + "    </tbody>\n"
                + "  </table><table class=\"module\" role=\"module\" data-type=\"social\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout: fixed;\" data-muid=\"39b0f8be-eccd-4c73-b428-357f98b77cbf\">\n"
                + "    <tbody>\n"
                + "      <tr>\n"
                + "        <td valign=\"top\" style=\"padding:15px 0px 15px 0px; font-size:6px; line-height:10px; background-color:#141479;\" align=\"right\">\n"
                + "          <table align=\"right\" style=\"-webkit-margin-start:auto;-webkit-margin-end:auto;\">\n"
                + "            <tbody><tr align=\"right\"><td style=\"padding: 0px 5px;\" class=\"social-icon-column\">\n"
                + "      <a role=\"social-icon-link\" href=\"http://www.facebook.com\" target=\"_blank\" alt=\"Facebook\" title=\"Facebook\" style=\"display:inline-block; background-color:#58a1d0; height:29px; width:29px; border-radius:16px; -webkit-border-radius:16px; -moz-border-radius:16px;\">\n"
                + "        <img role=\"social-icon\" alt=\"Facebook\" title=\"Facebook\" src=\"https://mc.sendgrid.com/assets/social/white/facebook.png\" style=\"height:29px; width:29px;\" height=\"29\" width=\"29\">\n"
                + "      </a>\n"
                + "    </td><td style=\"padding: 0px 5px;\" class=\"social-icon-column\">\n"
                + "      <a role=\"social-icon-link\" href=\"http://www.facebook.com\" target=\"_blank\" alt=\"Twitter\" title=\"Twitter\" style=\"display:inline-block; background-color:#58a1d0; height:29px; width:29px; border-radius:16px; -webkit-border-radius:16px; -moz-border-radius:16px;\">\n"
                + "        <img role=\"social-icon\" alt=\"Twitter\" title=\"Twitter\" src=\"https://mc.sendgrid.com/assets/social/white/twitter.png\" style=\"height:29px; width:29px;\" height=\"29\" width=\"29\">\n"
                + "      </a>\n"
                + "    </td><td style=\"padding: 0px 5px;\" class=\"social-icon-column\">\n"
                + "      <a role=\"social-icon-link\" href=\"http://www.facebook.com\" target=\"_blank\" alt=\"Instagram\" title=\"Instagram\" style=\"display:inline-block; background-color:#58a1d0; height:29px; width:29px; border-radius:16px; -webkit-border-radius:16px; -moz-border-radius:16px;\">\n"
                + "        <img role=\"social-icon\" alt=\"Instagram\" title=\"Instagram\" src=\"https://mc.sendgrid.com/assets/social/white/instagram.png\" style=\"height:29px; width:29px;\" height=\"29\" width=\"29\">\n"
                + "      </a>\n"
                + "    </td><td style=\"padding: 0px 5px;\" class=\"social-icon-column\">\n"
                + "      <a role=\"social-icon-link\" href=\"www.google.com\" target=\"_blank\" alt=\"Pinterest\" title=\"Pinterest\" style=\"display:inline-block; background-color:#58a1d0; height:29px; width:29px; border-radius:16px; -webkit-border-radius:16px; -moz-border-radius:16px;\">\n"
                + "        <img role=\"social-icon\" alt=\"Pinterest\" title=\"Pinterest\" src=\"https://mc.sendgrid.com/assets/social/white/pinterest.png\" style=\"height:29px; width:29px;\" height=\"29\" width=\"29\">\n"
                + "      </a>\n"
                + "    </td></tr></tbody>\n"
                + "          </table>\n"
                + "        </td>\n"
                + "      </tr>\n"
                + "    </tbody>\n"
                + "  </table><div data-role=\"module-unsubscribe\" class=\"module\" role=\"module\" data-type=\"unsubscribe\" style=\"background-color:#00004E; color:#ffffff; font-size:12px; line-height:20px; padding:16px 16px 16px 16px; text-align:Center;\" data-muid=\"4e838cf3-9892-4a6d-94d6-170e474d21e5\"><div class=\"Unsubscribe--addressLine\"></div><p style=\"font-size:12px; line-height:20px;\"><a target=\"_blank\" class=\"Unsubscribe--unsubscribeLink zzzzzzz\" href=\"{{{unsubscribe}}}\" style=\"color:#ffffff;\">Unsubscribe</a> - <a href=\"{{{unsubscribe_preferences}}}\" target=\"_blank\" class=\"Unsubscribe--unsubscribePreferences\" style=\"color:#ffffff;\">Unsubscribe Preferences</a></p></div><table class=\"module\" role=\"module\" data-type=\"text\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout: fixed;\" data-muid=\"0c6214e4-eb19-4800-b7ff-63b444bee411\" data-mc-module-version=\"2019-10-22\">\n"
                + "    <tbody>\n"
                + "      <tr>\n"
                + "        <td style=\"padding:5px 5px 5px 5px; line-height:22px; text-align:inherit; background-color:#eae3da;\" height=\"100%\" valign=\"top\" bgcolor=\"#eae3da\" role=\"module-content\"><div><div style=\"font-family: inherit; text-align: right\"><span style=\"font-size: 12px\">Trouble viewing this email? </span><a href=\"{{Weblink}}\"><span style=\"font-size: 12px\">View in browser</span></a></div><div></div></div></td>\n"
                + "      </tr>\n"
                + "    </tbody>\n"
                + "  </table></td>\n"
                + "                                      </tr>\n"
                + "                                    </table>\n"
                + "                                    <!--[if mso]>\n"
                + "                                  </td>\n"
                + "                                </tr>\n"
                + "                              </table>\n"
                + "                            </center>\n"
                + "                            <![endif]-->\n"
                + "                          </td>\n"
                + "                        </tr>\n"
                + "                      </table>\n"
                + "                    </td>\n"
                + "                  </tr>\n"
                + "                </table>\n"
                + "              </td>\n"
                + "            </tr>\n"
                + "          </table>\n"
                + "        </div>\n"
                + "      </center>\n"
                + "    </body>\n"
                + "  </html>";
    }
}