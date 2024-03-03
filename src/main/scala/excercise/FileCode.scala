package excercise

import java.io.File
import java.io.PrintWriter

object FileCode extends App {

  val fileObj = new File("input.txt")

  val print_Writer = new PrintWriter(fileObj)

  val singleData = """{"sap_acknowledgement":{"sap_msg_type_cd":"A","sap_country_cd":"US","sap_idoc_nbr":"0000000003115014","cill_unique_id":"CP0070MUS6302978B0000000010","interface_id":"I01412","process_area_nm":"GL","message_type_ind":"E","response_received_dt":"20220829","response_received_tm":"091011","retrigger_ind":"X","fiscal_yr":"0000","total_debit_amt":71833.59,"total_credit_amt":-71833.59,"doc_dt":"20220503","posting_dt":"20220503","doc_line_cnt":952,"sap_doc_process_dt":"20220829","sap_doc_process_tm":"091610","message_detl":[{"error_desc":"Not Classified","message_desc":"Error in document: BKPFF $ QF4CLNT910","error_cd":"0004"},{"error_desc":"Not Classified","message_desc":"Period 004\/2023 is not open for account type K and G\/L 2164001","error_cd":"0004"}]},"isValid":true}"""

  val content = singleData * 600000

  // Writing to the file
  print_Writer.write(content)

  // Closing printwriter
  print_Writer.close()

}
