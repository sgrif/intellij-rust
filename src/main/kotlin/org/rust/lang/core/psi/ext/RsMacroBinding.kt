/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.rust.lang.core.psi.ext

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.SearchScope
import org.rust.lang.core.psi.RsMacro
import org.rust.lang.core.psi.RsMacroBinding

abstract class RsMacroBindingImplMixin(node: ASTNode) : RsNamedElementImpl(node), RsMacroBinding {

    override fun getNameIdentifier(): PsiElement? = metaVarIdentifier

    override fun getUseScope(): SearchScope {
        val owner = contextStrict<RsMacro>() ?: error("Macro binding outside of a macro")
        return LocalSearchScope(owner)
    }
}

val RsMacroBinding.fragmentSpecifier: String?
    get() = identifier?.text
