package org.apache.ctakes.dictionary.lookup2.ae;

import org.apache.ctakes.core.resource.FileLocator;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.resource.ResourceInitializationException;

import java.io.IOException;
import java.io.InputStream;


/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 9/23/2014
 */
final public class DictionaryLookupFactory {

   private DictionaryLookupFactory() {
   }

   /**
    * @return a description for a
    * @throws ResourceInitializationException
    */
   public static AnalysisEngineDescription createDefaultDictionaryLookupDescription()
         throws ResourceInitializationException {
      return AnalysisEngineFactory.createEngineDescription( DefaultJCasTermAnnotator.class );
   }

   public static AnalysisEngineDescription createCustomDictionaryLookupDescription( final String descriptorPath )
         throws ResourceInitializationException {
      checkDescriptorPath( descriptorPath );
      return AnalysisEngineFactory.createEngineDescription( DefaultJCasTermAnnotator.class,
            JCasTermAnnotator.DICTIONARY_DESCRIPTOR_KEY,
            descriptorPath );
   }

   public static AnalysisEngineDescription createOverlapDictionaryLookupDescription()
         throws ResourceInitializationException {
      return AnalysisEngineFactory.createEngineDescription( OverlapJCasTermAnnotator.class );
   }

   public static AnalysisEngineDescription createCustomOverlapDictionaryLookupDescription( final String descriptorPath )
         throws ResourceInitializationException {
      checkDescriptorPath( descriptorPath );
      return AnalysisEngineFactory.createEngineDescription( OverlapJCasTermAnnotator.class,
            JCasTermAnnotator.DICTIONARY_DESCRIPTOR_KEY,
            descriptorPath );
   }

   /**
    * Ensure that the given descriptor path is valid; Does not ensure the path points to a file with valid format
    *
    * @param descriptorPath -
    * @throws ResourceInitializationException if an input stream cannot be opened using the given path
    */
   static private void checkDescriptorPath( final String descriptorPath ) throws ResourceInitializationException {
      // At this time, FileLocator.getAsStream() cannot return null, but this may help in the future
      try ( InputStream descriptorStream = FileLocator.getAsStream( descriptorPath ) ) {
         if ( descriptorStream == null ) {
            throw new ResourceInitializationException( new IOException( "Cannot open "
                                                                        + descriptorPath + " as stream" ) );
         }
      } catch ( IOException ioE ) {
         throw new ResourceInitializationException( ioE );
      }
   }


}
