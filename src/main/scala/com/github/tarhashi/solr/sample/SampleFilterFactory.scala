package com.github.tarhashi.solr.sample

import org.apache.lucene.analysis.TokenStream
import org.apache.solr.analysis.BaseTokenFilterFactory
import org.apache.solr.analysis.TokenFilterFactory

import com.github.tarhashi.lucene.sample.SampleFilter

class SampleFilterFactory extends BaseTokenFilterFactory {
  
  override def create(input: TokenStream) : TokenStream = {
    new SampleFilter(input)
  }

}
