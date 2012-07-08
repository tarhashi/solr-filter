package com.github.tarhashi.lucene.sample

import org.apache.lucene.analysis.TokenFilter
import org.apache.lucene.analysis.TokenStream
import scala.collection.mutable.ArrayBuffer
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
import org.apache.lucene.analysis.ja.tokenattributes.ReadingAttribute

class SampleFilter(input: TokenStream) extends TokenFilter(input) {
  
  private val termAttr = addAttribute(classOf[CharTermAttribute]);
  private val readingAttr = addAttribute(classOf[ReadingAttribute]);
  
  def incrementToken(): Boolean = { 
    if (input.incrementToken) {
      val str = termAttr.toString
      termAttr.setEmpty.append(convertHiraganaToKatakana(str))
      true
    } else {
      false 
    }
  }
  
  /**
   * ひらがなをカタカナに変換する
   */
  private def convertHiraganaToKatakana(str:String):String = {
    var sb = new StringBuilder
    val map = (('ぁ' to 'ん').zip(('ァ' to 'ン'))).toMap
    for(c <- str) sb.append(map.getOrElse(c, c))
    sb.toString
  }

}