/* ---- Subversion and License Info ---------------------------------------------
 * $Revision: 46 $
 * $Date: 2008-05-12 09:04:19 -0700 (Mon, 12 May 2008) $
 * $Author: gryb_info $
 * $HeadURL: https://xacmllight.svn.sourceforge.net/svnroot/xacmllight/trunk/pdp/src/main/java/info/gryb/xacml/pdp/Constants.java $
 * $Id: Constants.java 46 2008-05-12 16:04:19Z gryb_info $
 *-----------------------------------------------------------------------------
 * 
 * Copyright 2008 Oleg Gryb
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *----------------------------------------------------------------------------- 
 */

package techDecision.xacmlPdp.ws;
import org.apache.xmlbeans.XmlOptions;

public class Constants {

    public static final String REPO_URI = "gryb:info:policyset:repo";
    public static final String XPATH_DEF_VER = "http://www.w3.org/TR/1999/Rec-xpath-19991116";
    public static final String XACML_CTX_URI = "urn:oasis:names:tc:xacml:2.0:context:schema:os";
    public static final String XACML_CTX_PREFIX = "xacml-ctx";

    // Implemented Elements
	
    public static final String E_CONDITION = "os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType";
    public static final String E_RULE = "os.schema.policy._0._2.xacml.tc.names.oasis.RuleType";
    
    public static final String E_ATTRIBUTE_DESIGNATOR = "os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType";
//  the following elements implemented by E_ATTRIBUTE_DESIGNATOR:    
//    public static final String E_ACTIONATTRIBUTEDESIGNATOR = "os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorType";
//    public static final String E_ENVIRONMENTATTRIBUTEDESIGNATOR = "os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentAttributeDesignatorType";
//    public static final String E_RESOURCEATTRIBUTEDESIGNATOR = "os.schema.policy._0._2.xacml.tc.names.oasis.ResourceAttributeDesignatorType";
    
    public static final String E_ATTRIBUTE_SELECTOR = "os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType";
    public static final String E_SUBJECTATTRIBUTEDESIGNATOR = "os.schema.policy._0._2.xacml.tc.names.oasis.SubjectAttributeDesignatorType";

    public static final String E_APPLY = "os.schema.policy._0._2.xacml.tc.names.oasis.ApplyType";
    public static final String E_ATTRIBUTEVALUE = "os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType";
    public static final String E_RESOURCEMATCH = "os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType";
    public static final String E_SUBJECTMATCH = "os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType";
    public static final String E_ACTIONMATCH = "os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType";
    public static final String E_ENVIRONMENTMATCH = "os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType";

//  The type below comes from xmlbeans bug: it generates this type instead of abstract Expression type
    public static final String E_XmlAnyTypeImpl = "org.apache.xmlbeans.impl.values.XmlAnyTypeImpl";
    public static final String E_XmlStringImpl = "org.apache.xmlbeans.impl.values.XmlStringImpl";
//    public static final String E_EXPRESSION = "os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType";

    public static final String E_TARGET = "os.schema.policy._0._2.xacml.tc.names.oasis.TargetType";
    public static final String E_POLICY = "os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType";
    public static final String E_POLICYSET = "os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType";
	
    public static final String E_CONTEXT_ACTION = "os.schema.context._0._2.xacml.tc.names.oasis.ActionType";
    public static final String E_CONTEXT_ATTRIBUTE = "os.schema.context._0._2.xacml.tc.names.oasis.AttributeType";
    public static final String E_CONTEXT_ATTRIBUTEVALUE = "os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType";
    public static final String E_CONTEXT_DECISION = "os.schema.context._0._2.xacml.tc.names.oasis.DecisionType";
    public static final String E_CONTEXT_ENVIRONMENT = "os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType";
    public static final String E_CONTEXT_REQUEST = "os.schema.context._0._2.xacml.tc.names.oasis.RequestType";
    public static final String E_CONTEXT_RESOURCE = "os.schema.context._0._2.xacml.tc.names.oasis.ResourceType";
    public static final String E_CONTEXT_RESPONSE = "os.schema.context._0._2.xacml.tc.names.oasis.ResponseType";
    public static final String E_CONTEXT_RESULT = "os.schema.context._0._2.xacml.tc.names.oasis.ResultType";
    public static final String E_CONTEXT_STATUS = "os.schema.context._0._2.xacml.tc.names.oasis.StatusType";
    public static final String E_CONTEXT_STATUSCODE = "os.schema.context._0._2.xacml.tc.names.oasis.StatusCodeType";
    public static final String E_CONTEXT_SUBJECT = "os.schema.context._0._2.xacml.tc.names.oasis.SubjectType";
    public static final String E_DESCRIPTION = "os.schema.policy._0._2.xacml.tc.names.oasis.DescriptionType";
    public static final String E_RESOURCE = "os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType";
    public static final String E_RESOURCES = "os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType";
    public static final String E_SUBJECT = "os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType";
    public static final String E_SUBJECTS = "os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType";
    public static final String E_ACTION = "os.schema.policy._0._2.xacml.tc.names.oasis.ActionType";
    public static final String E_ACTIONS = "os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType";
    public static final String E_ENVIRONMENT = "os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentType";
    public static final String E_ENVIRONMENTS = "os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType";
    public static final String E_FUNCTION = "os.schema.policy._0._2.xacml.tc.names.oasis.FunctionType";
    public static final String E_VARIABLEDEFINITION = "os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType";
    public static final String E_VARIABLEREFERENCE = "os.schema.policy._0._2.xacml.tc.names.oasis.VariableReferenceType";
    public static final String E_CONTEXT_MISSINGATTRIBUTEDETAIL = "os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType";

    
	// Elements to be implemented
    
    public static final String E_POLICYIDREFERENCE = "os.schema.policy._0._2.xacml.tc.names.oasis.PolicyIdReferenceType";
    public static final String E_POLICYSETIDREFERENCE = "os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetIdReferenceType";
	

    // Implemented Functions
	
	public static final String F_AND = "urn:oasis:names:tc:xacml:1.0:function:and";
    public static final String F_OR = "urn:oasis:names:tc:xacml:1.0:function:or";
    public static final String F_STRING_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:string-equal";
    public static final String F_INTEGER_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:integer-equal";
    public static final String F_DOUBLE_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:double-equal";
    public static final String F_STRING_LESS_THAN = "urn:oasis:names:tc:xacml:1.0:function:string-less-than";
    public static final String F_INTEGER_LESS_THAN = "urn:oasis:names:tc:xacml:1.0:function:integer-less-than";
    public static final String F_DOUBLE_LESS_THAN = "urn:oasis:names:tc:xacml:1.0:function:double-less-than";
    public static final String F_INTEGER_ONE_AND_ONLY = "urn:oasis:names:tc:xacml:1.0:function:integer-one-and-only";
    public static final String F_DOUBLE_ONE_AND_ONLY = "urn:oasis:names:tc:xacml:1.0:function:double-one-and-only";
    public static final String F_STRING_ONE_AND_ONLY = "urn:oasis:names:tc:xacml:1.0:function:string-one-and-only";
    public static final String F_BOOLEAN_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:boolean-equal";
    public static final String F_NOT = "urn:oasis:names:tc:xacml:1.0:function:not";
    public static final String F_INTEGER_GREATER_THAN = "urn:oasis:names:tc:xacml:1.0:function:integer-greater-than";
    public static final String F_INTEGER_GREATER_THAN_OR_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:integer-greater-than-or-equal";
    public static final String F_INTEGER_LESS_THAN_OR_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:integer-less-than-or-equal";
    public static final String F_DOUBLE_GREATER_THAN = "urn:oasis:names:tc:xacml:1.0:function:double-greater-than";
    public static final String F_DOUBLE_GREATER_THAN_OR_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:double-greater-than-or-equal";
    public static final String F_DOUBLE_LESS_THAN_OR_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:double-less-than-or-equal";
    public static final String F_STRING_GREATER_THAN = "urn:oasis:names:tc:xacml:1.0:function:string-greater-than";
    public static final String F_STRING_GREATER_THAN_OR_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:string-greater-than-or-equal";
    public static final String F_STRING_LESS_THAN_OR_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:string-less-than-or-equal";
    public static final String F_INTEGER_ADD = "urn:oasis:names:tc:xacml:1.0:function:integer-add";
    public static final String F_DOUBLE_ADD = "urn:oasis:names:tc:xacml:1.0:function:double-add";
    public static final String F_INTEGER_SUBTRACT = "urn:oasis:names:tc:xacml:1.0:function:integer-subtract";
    public static final String F_DOUBLE_SUBTRACT = "urn:oasis:names:tc:xacml:1.0:function:double-subtract";
    public static final String F_INTEGER_MULTIPLY = "urn:oasis:names:tc:xacml:1.0:function:integer-multiply";
    public static final String F_DOUBLE_MULTIPLY = "urn:oasis:names:tc:xacml:1.0:function:double-multiply";
    public static final String F_INTEGER_DIVIDE = "urn:oasis:names:tc:xacml:1.0:function:integer-divide";
    public static final String F_DOUBLE_DIVIDE = "urn:oasis:names:tc:xacml:1.0:function:double-divide";
    public static final String F_INTEGER_MOD = "urn:oasis:names:tc:xacml:1.0:function:integer-mod";
    public static final String F_INTEGER_ABS = "urn:oasis:names:tc:xacml:1.0:function:integer-abs";
    public static final String F_DOUBLE_ABS = "urn:oasis:names:tc:xacml:1.0:function:double-abs";
    public static final String F_ROUND = "urn:oasis:names:tc:xacml:1.0:function:round";
    public static final String F_FLOOR = "urn:oasis:names:tc:xacml:1.0:function:floor";
    public static final String F_DOUBLE_TO_INTEGER = "urn:oasis:names:tc:xacml:1.0:function:double-to-integer";
    public static final String F_INTEGER_TO_DOUBLE = "urn:oasis:names:tc:xacml:1.0:function:integer-to-double";
    public static final String F_DATE_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:date-equal";
    public static final String F_TIME_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:time-equal";
    public static final String F_DATETIME_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:dateTime-equal";
    public static final String F_TIME_GREATER_THAN = "urn:oasis:names:tc:xacml:1.0:function:time-greater-than";
    public static final String F_TIME_GREATER_THAN_OR_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:time-greater-than-or-equal";
    public static final String F_TIME_LESS_THAN = "urn:oasis:names:tc:xacml:1.0:function:time-less-than";
    public static final String F_TIME_LESS_THAN_OR_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:time-less-than-or-equal";
    public static final String F_TIME_IN_RANGE = "urn:oasis:names:tc:xacml:2.0:function:time-in-range";
    public static final String F_DATETIME_GREATER_THAN = "urn:oasis:names:tc:xacml:1.0:function:dateTime-greater-than";
    public static final String F_DATETIME_GREATER_THAN_OR_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:dateTime-greater-than-or-equal";
    public static final String F_DATETIME_LESS_THAN = "urn:oasis:names:tc:xacml:1.0:function:dateTime-less-than";
    public static final String F_DATETIME_LESS_THAN_OR_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:dateTime-less-than-or-equal";
    public static final String F_DATE_GREATER_THAN = "urn:oasis:names:tc:xacml:1.0:function:date-greater-than";
    public static final String F_DATE_GREATER_THAN_OR_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:date-greater-than-or-equal";
    public static final String F_DATE_LESS_THAN = "urn:oasis:names:tc:xacml:1.0:function:date-less-than";
    public static final String F_DATE_LESS_THAN_OR_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:date-less-than-or-equal";
    public static final String F_DAYTIMEDURATION_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:dayTimeDuration-equal";
    public static final String F_YEARMONTHDURATION_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:yearMonthDuration-equal";
    public static final String F_ANYURI_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:anyURI-equal";
    public static final String F_DATETIME_ADD_DAYTIMEDURATION = "urn:oasis:names:tc:xacml:1.0:function:dateTime-add-dayTimeDuration";
    public static final String F_DATETIME_ADD_YEARMONTHDURATION = "urn:oasis:names:tc:xacml:1.0:function:dateTime-add-yearMonthDuration";
    public static final String F_DATETIME_SUBTRACT_DAYTIMEDURATION = "urn:oasis:names:tc:xacml:1.0:function:dateTime-subtract-dayTimeDuration";
    public static final String F_DATETIME_SUBTRACT_YEARMONTHDURATION = "urn:oasis:names:tc:xacml:1.0:function:dateTime-subtract-yearMonthDuration";
    public static final String F_DATE_ADD_YEARMONTHDURATION = "urn:oasis:names:tc:xacml:1.0:function:date-add-yearMonthDuration";
    public static final String F_DATE_SUBTRACT_YEARMONTHDURATION = "urn:oasis:names:tc:xacml:1.0:function:date-subtract-yearMonthDuration";
    public static final String F_TIME_ONE_AND_ONLY = "urn:oasis:names:tc:xacml:1.0:function:time-one-and-only";
    public static final String F_DATETIME_ONE_AND_ONLY = "urn:oasis:names:tc:xacml:1.0:function:dateTime-one-and-only";
    public static final String F_DATE_ONE_AND_ONLY = "urn:oasis:names:tc:xacml:1.0:function:date-one-and-only";
    public static final String F_BOOLEAN_ONE_AND_ONLY = "urn:oasis:names:tc:xacml:1.0:function:boolean-one-and-only";
    public static final String F_ANYURI_ONE_AND_ONLY = "urn:oasis:names:tc:xacml:1.0:function:anyURI-one-and-only";
    public static final String F_DAYTIMEDURATION_ONE_AND_ONLY = "urn:oasis:names:tc:xacml:1.0:function:dayTimeDuration-one-and-only";
    public static final String F_YEARMONTHDURATION_ONE_AND_ONLY = "urn:oasis:names:tc:xacml:1.0:function:yearMonthDuration-one-and-only";
    public static final String F_STRING_NORMALIZE_SPACE = "urn:oasis:names:tc:xacml:1.0:function:string-normalize-space";
    public static final String F_STRING_NORMALIZE_TO_LOWER_CASE = "urn:oasis:names:tc:xacml:1.0:function:string-normalize-to-lower-case";
    public static final String F_STRING_BAG_SIZE = "urn:oasis:names:tc:xacml:1.0:function:string-bag-size";
    public static final String F_STRING_IS_IN = "urn:oasis:names:tc:xacml:1.0:function:string-is-in";
    public static final String F_STRING_BAG = "urn:oasis:names:tc:xacml:1.0:function:string-bag";
    public static final String F_BOOLEAN_BAG_SIZE = "urn:oasis:names:tc:xacml:1.0:function:boolean-bag-size";
    public static final String F_BOOLEAN_IS_IN = "urn:oasis:names:tc:xacml:1.0:function:boolean-is-in";
    public static final String F_BOOLEAN_BAG = "urn:oasis:names:tc:xacml:1.0:function:boolean-bag";
    public static final String F_INTEGER_BAG_SIZE = "urn:oasis:names:tc:xacml:1.0:function:integer-bag-size";
    public static final String F_INTEGER_IS_IN = "urn:oasis:names:tc:xacml:1.0:function:integer-is-in";
    public static final String F_INTEGER_BAG = "urn:oasis:names:tc:xacml:1.0:function:integer-bag";
    public static final String F_DOUBLE_BAG_SIZE = "urn:oasis:names:tc:xacml:1.0:function:double-bag-size";
    public static final String F_DOUBLE_IS_IN = "urn:oasis:names:tc:xacml:1.0:function:double-is-in";
    public static final String F_DOUBLE_BAG = "urn:oasis:names:tc:xacml:1.0:function:double-bag";
    public static final String F_TIME_BAG_SIZE = "urn:oasis:names:tc:xacml:1.0:function:time-bag-size";
    public static final String F_TIME_IS_IN = "urn:oasis:names:tc:xacml:1.0:function:time-is-in";
    public static final String F_TIME_BAG = "urn:oasis:names:tc:xacml:1.0:function:time-bag";
    public static final String F_DATE_BAG_SIZE = "urn:oasis:names:tc:xacml:1.0:function:date-bag-size";
    public static final String F_DATE_IS_IN = "urn:oasis:names:tc:xacml:1.0:function:date-is-in";
    public static final String F_DATE_BAG = "urn:oasis:names:tc:xacml:1.0:function:date-bag";
    public static final String F_DATETIME_BAG_SIZE = "urn:oasis:names:tc:xacml:1.0:function:dateTime-bag-size";
    public static final String F_DATETIME_IS_IN = "urn:oasis:names:tc:xacml:1.0:function:dateTime-is-in";
    public static final String F_DATETIME_BAG = "urn:oasis:names:tc:xacml:1.0:function:dateTime-bag";
    public static final String F_ANYURI_BAG_SIZE = "urn:oasis:names:tc:xacml:1.0:function:anyURI-bag-size";
    public static final String F_ANYURI_IS_IN = "urn:oasis:names:tc:xacml:1.0:function:anyURI-is-in";
    public static final String F_ANYURI_BAG = "urn:oasis:names:tc:xacml:1.0:function:anyURI-bag";
    public static final String F_STRING_INTERSECTION = "urn:oasis:names:tc:xacml:1.0:function:string-intersection";
    public static final String F_STRING_AT_LEAST_ONE_MEMBER_OF = "urn:oasis:names:tc:xacml:1.0:function:string-at-least-one-member-of";
    public static final String F_STRING_UNION = "urn:oasis:names:tc:xacml:1.0:function:string-union";
    public static final String F_STRING_SUBSET = "urn:oasis:names:tc:xacml:1.0:function:string-subset";
    public static final String F_STRING_SET_EQUALS = "urn:oasis:names:tc:xacml:1.0:function:string-set-equals";
    public static final String F_BOOLEAN_INTERSECTION = "urn:oasis:names:tc:xacml:1.0:function:boolean-intersection";
    public static final String F_BOOLEAN_AT_LEAST_ONE_MEMBER_OF = "urn:oasis:names:tc:xacml:1.0:function:boolean-at-least-one-member-of";
    public static final String F_BOOLEAN_UNION = "urn:oasis:names:tc:xacml:1.0:function:boolean-union";
    public static final String F_BOOLEAN_SUBSET = "urn:oasis:names:tc:xacml:1.0:function:boolean-subset";
    public static final String F_BOOLEAN_SET_EQUALS = "urn:oasis:names:tc:xacml:1.0:function:boolean-set-equals";
    public static final String F_INTEGER_INTERSECTION = "urn:oasis:names:tc:xacml:1.0:function:integer-intersection";
    public static final String F_INTEGER_AT_LEAST_ONE_MEMBER_OF = "urn:oasis:names:tc:xacml:1.0:function:integer-at-least-one-member-of";
    public static final String F_INTEGER_UNION = "urn:oasis:names:tc:xacml:1.0:function:integer-union";
    public static final String F_INTEGER_SUBSET = "urn:oasis:names:tc:xacml:1.0:function:integer-subset";
    public static final String F_INTEGER_SET_EQUALS = "urn:oasis:names:tc:xacml:1.0:function:integer-set-equals";
    public static final String F_DOUBLE_INTERSECTION = "urn:oasis:names:tc:xacml:1.0:function:double-intersection";
    public static final String F_DOUBLE_AT_LEAST_ONE_MEMBER_OF = "urn:oasis:names:tc:xacml:1.0:function:double-at-least-one-member-of";
    public static final String F_DOUBLE_UNION = "urn:oasis:names:tc:xacml:1.0:function:double-union";
    public static final String F_DOUBLE_SUBSET = "urn:oasis:names:tc:xacml:1.0:function:double-subset";
    public static final String F_DOUBLE_SET_EQUALS = "urn:oasis:names:tc:xacml:1.0:function:double-set-equals";
    public static final String F_TIME_INTERSECTION = "urn:oasis:names:tc:xacml:1.0:function:time-intersection";
    public static final String F_TIME_AT_LEAST_ONE_MEMBER_OF = "urn:oasis:names:tc:xacml:1.0:function:time-at-least-one-member-of";
    public static final String F_TIME_UNION = "urn:oasis:names:tc:xacml:1.0:function:time-union";
    public static final String F_TIME_SUBSET = "urn:oasis:names:tc:xacml:1.0:function:time-subset";
    public static final String F_TIME_SET_EQUALS = "urn:oasis:names:tc:xacml:1.0:function:time-set-equals";
    public static final String F_DATE_INTERSECTION = "urn:oasis:names:tc:xacml:1.0:function:date-intersection";
    public static final String F_DATE_AT_LEAST_ONE_MEMBER_OF = "urn:oasis:names:tc:xacml:1.0:function:date-at-least-one-member-of";
    public static final String F_DATE_UNION = "urn:oasis:names:tc:xacml:1.0:function:date-union";
    public static final String F_DATE_SUBSET = "urn:oasis:names:tc:xacml:1.0:function:date-subset";
    public static final String F_DATE_SET_EQUALS = "urn:oasis:names:tc:xacml:1.0:function:date-set-equals";
    public static final String F_DATETIME_INTERSECTION = "urn:oasis:names:tc:xacml:1.0:function:dateTime-intersection";
    public static final String F_DATETIME_AT_LEAST_ONE_MEMBER_OF = "urn:oasis:names:tc:xacml:1.0:function:dateTime-at-least-one-member-of";
    public static final String F_DATETIME_UNION = "urn:oasis:names:tc:xacml:1.0:function:dateTime-union";
    public static final String F_DATETIME_SUBSET = "urn:oasis:names:tc:xacml:1.0:function:dateTime-subset";
    public static final String F_DATETIME_SET_EQUALS = "urn:oasis:names:tc:xacml:1.0:function:dateTime-set-equals";
    public static final String F_ANYURI_INTERSECTION = "urn:oasis:names:tc:xacml:1.0:function:anyURI-intersection";
    public static final String F_ANYURI_AT_LEAST_ONE_MEMBER_OF = "urn:oasis:names:tc:xacml:1.0:function:anyURI-at-least-one-member-of";
    public static final String F_ANYURI_UNION = "urn:oasis:names:tc:xacml:1.0:function:anyURI-union";
    public static final String F_ANYURI_SUBSET = "urn:oasis:names:tc:xacml:1.0:function:anyURI-subset";
    public static final String F_ANYURI_SET_EQUALS = "urn:oasis:names:tc:xacml:1.0:function:anyURI-set-equals";
    public static final String F_DAYTIMEDURATION_INTERSECTION = "urn:oasis:names:tc:xacml:1.0:function:dayTimeDuration-intersection";
    public static final String F_DAYTIMEDURATION_AT_LEAST_ONE_MEMBER_OF = "urn:oasis:names:tc:xacml:1.0:function:dayTimeDuration-at-least-one-member-of";
    public static final String F_DAYTIMEDURATION_UNION = "urn:oasis:names:tc:xacml:1.0:function:dayTimeDuration-union";
    public static final String F_DAYTIMEDURATION_SUBSET = "urn:oasis:names:tc:xacml:1.0:function:dayTimeDuration-subset";
    public static final String F_DAYTIMEDURATION_SET_EQUALS = "urn:oasis:names:tc:xacml:1.0:function:dayTimeDuration-set-equals";
    public static final String F_YEARMONTHDURATION_INTERSECTION = "urn:oasis:names:tc:xacml:1.0:function:yearMonthDuration-intersection";
    public static final String F_YEARMONTHDURATION_AT_LEAST_ONE_MEMBER_OF = "urn:oasis:names:tc:xacml:1.0:function:yearMonthDuration-at-least-one-member-of";
    public static final String F_YEARMONTHDURATION_UNION = "urn:oasis:names:tc:xacml:1.0:function:yearMonthDuration-union";
    public static final String F_YEARMONTHDURATION_SUBSET = "urn:oasis:names:tc:xacml:1.0:function:yearMonthDuration-subset";
    public static final String F_YEARMONTHDURATION_SET_EQUALS = "urn:oasis:names:tc:xacml:1.0:function:yearMonthDuration-set-equals";
    public static final String F_DAYTIMEDURATION_BAG_SIZE = "urn:oasis:names:tc:xacml:1.0:function:dayTimeDuration-bag-size";
    public static final String F_DAYTIMEDURATION_IS_IN = "urn:oasis:names:tc:xacml:1.0:function:dayTimeDuration-is-in";
    public static final String F_DAYTIMEDURATION_BAG = "urn:oasis:names:tc:xacml:1.0:function:dayTimeDuration-bag";
    public static final String F_YEARMONTHDURATION_BAG_SIZE = "urn:oasis:names:tc:xacml:1.0:function:yearMonthDuration-bag-size";
    public static final String F_YEARMONTHDURATION_IS_IN = "urn:oasis:names:tc:xacml:1.0:function:yearMonthDuration-is-in";
    public static final String F_YEARMONTHDURATION_BAG = "urn:oasis:names:tc:xacml:1.0:function:yearMonthDuration-bag";
    public static final String F_STRING_CONCATENATE = "urn:oasis:names:tc:xacml:2.0:function:string-concatenate";
    public static final String F_URI_STRING_CONCATENATE = "urn:oasis:names:tc:xacml:2.0:function:uri-string-concatenate";
    public static final String F_HEXBINARY_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:hexBinary-equal";
    public static final String F_HEXBINARY_ONE_AND_ONLY = "urn:oasis:names:tc:xacml:1.0:function:hexBinary-one-and-only";
    public static final String F_HEXBINARY_BAG_SIZE = "urn:oasis:names:tc:xacml:1.0:function:hexBinary-bag-size";
    public static final String F_HEXBINARY_IS_IN = "urn:oasis:names:tc:xacml:1.0:function:hexBinary-is-in";
    public static final String F_HEXBINARY_BAG = "urn:oasis:names:tc:xacml:1.0:function:hexBinary-bag";
    public static final String F_HEXBINARY_INTERSECTION = "urn:oasis:names:tc:xacml:1.0:function:hexBinary-intersection";
    public static final String F_HEXBINARY_AT_LEAST_ONE_MEMBER_OF = "urn:oasis:names:tc:xacml:1.0:function:hexBinary-at-least-one-member-of";
    public static final String F_HEXBINARY_UNION = "urn:oasis:names:tc:xacml:1.0:function:hexBinary-union";
    public static final String F_HEXBINARY_SUBSET = "urn:oasis:names:tc:xacml:1.0:function:hexBinary-subset";
    public static final String F_HEXBINARY_SET_EQUALS = "urn:oasis:names:tc:xacml:1.0:function:hexBinary-set-equals";
    public static final String F_BASE64BINARY_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:base64Binary-equal";
    public static final String F_BASE64BINARY_ONE_AND_ONLY = "urn:oasis:names:tc:xacml:1.0:function:base64Binary-one-and-only";
    public static final String F_BASE64BINARY_BAG_SIZE = "urn:oasis:names:tc:xacml:1.0:function:base64Binary-bag-size";
    public static final String F_BASE64BINARY_IS_IN = "urn:oasis:names:tc:xacml:1.0:function:base64Binary-is-in";
    public static final String F_BASE64BINARY_BAG = "urn:oasis:names:tc:xacml:1.0:function:base64Binary-bag";
    public static final String F_BASE64BINARY_INTERSECTION = "urn:oasis:names:tc:xacml:1.0:function:base64Binary-intersection";
    public static final String F_BASE64BINARY_AT_LEAST_ONE_MEMBER_OF = "urn:oasis:names:tc:xacml:1.0:function:base64Binary-at-least-one-member-of";
    public static final String F_BASE64BINARY_UNION = "urn:oasis:names:tc:xacml:1.0:function:base64Binary-union";
    public static final String F_BASE64BINARY_SUBSET = "urn:oasis:names:tc:xacml:1.0:function:base64Binary-subset";
    public static final String F_BASE64BINARY_SET_EQUALS = "urn:oasis:names:tc:xacml:1.0:function:base64Binary-set-equals";
    public static final String F_ANY_OF = "urn:oasis:names:tc:xacml:1.0:function:any-of";
    public static final String F_ALL_OF = "urn:oasis:names:tc:xacml:1.0:function:all-of";
    public static final String F_ANY_OF_ANY = "urn:oasis:names:tc:xacml:1.0:function:any-of-any";
    public static final String F_ALL_OF_ANY = "urn:oasis:names:tc:xacml:1.0:function:all-of-any";
    public static final String F_ANY_OF_ALL = "urn:oasis:names:tc:xacml:1.0:function:any-of-all";
    public static final String F_ALL_OF_ALL = "urn:oasis:names:tc:xacml:1.0:function:all-of-all";
    public static final String F_MAP = "urn:oasis:names:tc:xacml:1.0:function:map";
    public static final String F_N_OF = "urn:oasis:names:tc:xacml:1.0:function:n-of";
    public static final String F_STRING_REGEXP_MATCH = "urn:oasis:names:tc:xacml:1.0:function:string-regexp-match";
    public static final String F_ANYURI_REGEXP_MATCH = "urn:oasis:names:tc:xacml:2.0:function:anyURI-regexp-match";
    public static final String F_X500NAME_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:x500Name-equal";
    public static final String F_X500NAME_ONE_AND_ONLY = "urn:oasis:names:tc:xacml:1.0:function:x500Name-one-and-only";
    public static final String F_X500NAME_BAG_SIZE = "urn:oasis:names:tc:xacml:1.0:function:x500Name-bag-size";
    public static final String F_X500NAME_IS_IN = "urn:oasis:names:tc:xacml:1.0:function:x500Name-is-in";
    public static final String F_X500NAME_BAG = "urn:oasis:names:tc:xacml:1.0:function:x500Name-bag";
    public static final String F_X500NAME_REGEXP_MATCH = "urn:oasis:names:tc:xacml:2.0:function:x500Name-regexp-match";
    public static final String F_X500NAME_INTERSECTION = "urn:oasis:names:tc:xacml:1.0:function:x500Name-intersection";
    public static final String F_X500NAME_AT_LEAST_ONE_MEMBER_OF = "urn:oasis:names:tc:xacml:1.0:function:x500Name-at-least-one-member-of";
    public static final String F_X500NAME_UNION = "urn:oasis:names:tc:xacml:1.0:function:x500Name-union";
    public static final String F_X500NAME_SUBSET = "urn:oasis:names:tc:xacml:1.0:function:x500Name-subset";
    public static final String F_X500NAME_SET_EQUALS = "urn:oasis:names:tc:xacml:1.0:function:x500Name-set-equals";
    public static final String F_X500NAME_MATCH = "urn:oasis:names:tc:xacml:1.0:function:x500Name-match";
    public static final String F_RFC822NAME_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:rfc822Name-equal";
    public static final String F_RFC822NAME_ONE_AND_ONLY = "urn:oasis:names:tc:xacml:1.0:function:rfc822Name-one-and-only";
    public static final String F_RFC822NAME_BAG_SIZE = "urn:oasis:names:tc:xacml:1.0:function:rfc822Name-bag-size";
    public static final String F_RFC822NAME_IS_IN = "urn:oasis:names:tc:xacml:1.0:function:rfc822Name-is-in";
    public static final String F_RFC822NAME_BAG = "urn:oasis:names:tc:xacml:1.0:function:rfc822Name-bag";
    public static final String F_RFC822NAME_MATCH = "urn:oasis:names:tc:xacml:1.0:function:rfc822Name-match";
    public static final String F_RFC822NAME_REGEXP_MATCH = "urn:oasis:names:tc:xacml:2.0:function:rfc822Name-regexp-match";
    public static final String F_RFC822NAME_INTERSECTION = "urn:oasis:names:tc:xacml:1.0:function:rfc822Name-intersection";
    public static final String F_RFC822NAME_AT_LEAST_ONE_MEMBER_OF = "urn:oasis:names:tc:xacml:1.0:function:rfc822Name-at-least-one-member-of";
    public static final String F_RFC822NAME_UNION = "urn:oasis:names:tc:xacml:1.0:function:rfc822Name-union";
    public static final String F_RFC822NAME_SUBSET = "urn:oasis:names:tc:xacml:1.0:function:rfc822Name-subset";
    public static final String F_RFC822NAME_SET_EQUALS = "urn:oasis:names:tc:xacml:1.0:function:rfc822Name-set-equals";
    public static final String F_IPADDRESS_REGEXP_MATCH = "urn:oasis:names:tc:xacml:2.0:function:ipAddress-regexp-match";
    public static final String F_DNSNAME_REGEXP_MATCH = "urn:oasis:names:tc:xacml:2.0:function:dnsName-regexp-match";

    
    public static final String F_NODE_COUNT = "urn:oasis:names:tc:xacml:1.0:function:xpath-node-count";
    public static final String F_NODE_EQUAL = "urn:oasis:names:tc:xacml:1.0:function:xpath-node-equal";
    public static final String F_NODE_MATCH = "urn:oasis:names:tc:xacml:1.0:function:xpath-node-match";

    // To Be Implemented Functions


	// End of Functions
	
	public static final String STATUS_MISSING_ATTR = "urn:oasis:names:tc:xacml:1.0:status:missing-attribute";
    public static final String STATUS_OK = "urn:oasis:names:tc:xacml:1.0:status:ok";
    public static final String STATUS_PROCESSING_ERROR = "urn:oasis:names:tc:xacml:1.0:status:processing-error";
    public static final String STATUS_SYNATX_ERROR = "urn:oasis:names:tc:xacml:1.0:status:syntax-error";

    public static final String T_STRING = "http://www.w3.org/2001/XMLSchema#string";
    public static final String T_DATE = "http://www.w3.org/2001/XMLSchema#date";
    public static final String T_TIME = "http://www.w3.org/2001/XMLSchema#time";
    public static final String T_DATETIME = "http://www.w3.org/2001/XMLSchema#dateTime";
    public static final String T_INTEGER = "http://www.w3.org/2001/XMLSchema#integer";
    public static final String T_DOUBLE = "http://www.w3.org/2001/XMLSchema#double";
    public static final String T_BOOLEAN = "http://www.w3.org/2001/XMLSchema#boolean";
    public static final String T_DTDURATION = "http://www.w3.org/TR/2002/WD-xquery-operators-20020816#dayTimeDuration";
    public static final String T_YMDURATION = "http://www.w3.org/TR/2002/WD-xquery-operators-20020816#yearMonthDuration";
    public static final String T_URI = "http://www.w3.org/2001/XMLSchema#anyURI";
    public static final String T_HEXBINARY = "http://www.w3.org/2001/XMLSchema#hexBinary";
    public static final String T_BASE64 = "http://www.w3.org/2001/XMLSchema#base64Binary";
    public static final String T_RFC822NAME = "urn:oasis:names:tc:xacml:1.0:data-type:rfc822Name";
    public static final String T_X500NAME = "urn:oasis:names:tc:xacml:1.0:data-type:x500Name";
    public static final String T_IPADDRESS = "urn:oasis:names:tc:xacml:2.0:data-type:ipAddress";
    public static final String T_DNSNAME = "urn:oasis:names:tc:xacml:2.0:data-type:dnsName";
    
    // Predefined attributes
    
    public static final String T_CURRENT_TIME = "urn:oasis:names:tc:xacml:1.0:environment:current-time";
    public static final String T_CURRENT_DATE = "urn:oasis:names:tc:xacml:1.0:environment:current-date";
    public static final String T_CURRENT_DATETIME = "urn:oasis:names:tc:xacml:1.0:environment:current-dateTime";


    
    public static final String T_RESOURCE_ID = "urn:oasis:names:tc:xacml:1.0:resource:resource-id";
    public static final String T_RESOURCE_ID2 = "urn:oasis:names:tc:xacml:2.0:resource:resource-id";
    
    
    public static final String CONFIG_FILE = "info_gryb_xacml_config.xml";
    public static final String PSEUDO_ROOT_ID = "info:gryb:xacml:policySet:pseudo";
    
    public static final XmlOptions opts = new XmlOptions().setSavePrettyPrint().setSavePrettyPrintIndent(3);
	
}
